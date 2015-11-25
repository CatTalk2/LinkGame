//Activityʵ��

package org.lol.link;

import java.util.Timer;
import java.util.TimerTask;

import org.lol.link.board.GameService;
import org.lol.link.board.impl.GameServiceImpl;
import org.lol.link.object.GameConf;
import org.lol.link.object.LinkInfo;
import org.lol.link.view.GameView;
import org.lol.link.view.Piece;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Link extends Activity {
	private GameConf config;// ��Ϸ���ö���
	private GameService gameService;// ��Ϸҵ���߼��ӿ�
	private GameView gameView;// ��Ϸ����
	private Button startButton;// ��ʼ��ť
	private TextView timeTextView;// ��¼ʣ��ʱ���TextView
	private AlertDialog.Builder lostDialog;// ʧ�ܺ󵯳��ĶԻ���
	private AlertDialog.Builder successDialog;// ��Ϸʤ����ĶԻ���
	private Timer timer = new Timer();// ��ʱ��
	private int gameTime;// ��¼��Ϸ��ʣ��ʱ��
	private boolean isPlaying;// ��¼�Ƿ�����Ϸ״̬
	private Piece selected = null;// ��¼�Ѿ�ѡ�еķ���
	// ������Ч��SoundPool
	SoundPool soundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 8);
	int dis;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x123:
				timeTextView.setText("ʣ��ʱ�䣺 " + gameTime);
				gameTime--;
				// ʱ��С��0, ��Ϸʧ��
				if (gameTime < 0) {
					stopTimer();
					// ������Ϸ��״̬
					isPlaying = false;
					lostDialog.show();
					soundPool.play(dis, 1, 1, 0, 0, 1);
					return;
				}
				break;
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ʼ������
		init();
	}

	// ��ʼ����Ϸ�ķ�����
	private void init() {
		config = new GameConf(8, 9, 2, 10, 100000, this);
		// �õ���Ϸ�������
		gameView = (GameView) findViewById(R.id.gameView);
		// ��ȡ��ʾʣ��ʱ����ı���
		timeTextView = (TextView) findViewById(R.id.timeText);
		// ��ȡ��ʼ��ť
		startButton = (Button) this.findViewById(R.id.startButton);
		// ��ʼ����Ч
		dis = soundPool.load(this, R.raw.dis, 1);

		// ��ӱ������֣��˴��Կӣ�����
		Intent intent = new Intent(Link.this, MusicService.class);
		startService(intent);

		gameService = new GameServiceImpl(this.config);
		gameView.setGameService(gameService);

		// ��ʼ��ť����¼�����
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View source) {
				startGame(GameConf.DEFAULT_TIME);
			}
		});

		// ��Ϸ���������¼�����
		this.gameView.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View view, MotionEvent e) {
				if (!isPlaying) {
					return false;
				}
				if (e.getAction() == MotionEvent.ACTION_DOWN) {
					gameViewTouchDown(e);
				}
				if (e.getAction() == MotionEvent.ACTION_UP) {
					gameViewTouchUp(e);
				}
				return true;
			}
		});

		// ��Ϸʧ�ܵĶԻ���
		lostDialog = createDialog("Defeat", "��������Ϊ�ܣ�����һ����ɧ��", R.drawable.lost)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						startGame(GameConf.DEFAULT_TIME);
					}
				});
		// ��Ϸʤ���ĶԻ���
		successDialog = createDialog("Victory", "����Ϊ����ҫ��������ս",
				R.drawable.success).setPositiveButton("ȷ��",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						startGame(GameConf.DEFAULT_TIME);
					}
				});
	}

	// �������ֵ�ֹͣ����
	protected void onStop() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Link.this, MusicService.class);
		stopService(intent);
		super.onStop();
	}

	// ��ͣ��Ϸ
	@Override
	protected void onPause() {
		stopTimer();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// ���������Ϸ״̬��
		if (isPlaying) {
			// ��ʣ��ʱ����д��ʼ��Ϸ
			startGame(gameTime);
		}
		super.onResume();
	}

	// ������Ϸ����Ĵ�����
	private void gameViewTouchDown(MotionEvent event) // ��
	{
		Piece[][] pieces = gameService.getPieces();
		float touchX = event.getX();// ��ȡ�û������x����
		float touchY = event.getY();// ��ȡ�û������y����
		// �ɴ�������õ�ͼ�����
		Piece currentPiece = gameService.findPiece(touchX, touchY); // ��
		// ���û��ѡ���κ�Piece����(��������ĵط�û��ͼƬ), ��������ִ��
		if (currentPiece == null)
			return;
		// ��gameView�е�ѡ�з�����Ϊ��ǰ����
		this.gameView.setSelectedPiece(currentPiece);
		// ��ʾ֮ǰû��ѡ���κ�һ��Piece
		if (this.selected == null) {
			// ����ǰ������Ϊ��ѡ�еķ���, ���½�GamePanel����, ����������ִ��
			this.selected = currentPiece;
			this.gameView.postInvalidate();
			return;
		}
		// ��ʾ֮ǰ�Ѿ�ѡ����һ��
		if (this.selected != null) {
			// �������Ҫ��currentPiece��prePiece�����жϲ���������
			LinkInfo linkInfo = this.gameService.link(this.selected,
					currentPiece); // ��
			// ����Piece������linkInfoΪnull
			if (linkInfo == null) {
				// ������Ӳ��ɹ�, ����ǰ������Ϊѡ�з���
				this.selected = currentPiece;
				this.gameView.postInvalidate();
			} else {
				// ����ɹ�����
				handleSuccessLink(linkInfo, this.selected, currentPiece, pieces);
			}
		}
	}

	// ������Ϸ����Ĵ�����
	private void gameViewTouchUp(MotionEvent e) {
		this.gameView.postInvalidate();
	}

	// ��Ϸ��ʱ�Ĵ���
	private void startGame(int gameTime) {
		// ���֮ǰ��timer��δȡ����ȡ��timer
		if (this.timer != null) {
			stopTimer();
		}
		// ����������Ϸʱ��
		this.gameTime = gameTime;
		// �����Ϸʣ��ʱ��������Ϸʱ����ȣ���Ϊ���¿�ʼ����Ϸ
		if (gameTime == GameConf.DEFAULT_TIME) {
			// ��ʼ�µ���Ϸ��Ϸ
			gameView.startGame();
		}
		isPlaying = true;
		this.timer = new Timer();
		// ������ʱ����ÿ��ˢ��
		this.timer.schedule(new TimerTask() {
			public void run() {
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 1000);
		// ��ѡ�з�����Ϊnull��
		this.selected = null;
	}

	/*
	 * �ɹ����Ӻ�Ĵ���
	 */

	// �����߼��㷨���ж��Ƿ��������жϵ�ǰ��Ϸ״̬
	private void handleSuccessLink(LinkInfo linkInfo, Piece prePiece,
			Piece currentPiece, Piece[][] pieces) {
		// ���ǿ�������, ��GamePanel����LinkInfo
		this.gameView.setLinkInfo(linkInfo);
		this.gameView.setSelectedPiece(null);
		this.gameView.postInvalidate();
		// ������Piece�����������ɾ��
		pieces[prePiece.getIndexX()][prePiece.getIndexY()] = null;
		pieces[currentPiece.getIndexX()][currentPiece.getIndexY()] = null;
		// ��ѡ�еķ�������null��
		this.selected = null;
		// ������Ч
		soundPool.play(dis, 1, 1, 0, 0, 1);
		// �ж��Ƿ���ʣ�µķ���, ���û��, ��Ϸʤ��
		if (!this.gameService.hasPieces()) {
			// ��Ϸʤ�����Ի��򵯳�
			this.successDialog.show();
			// ֹͣ��ʱ��
			stopTimer();
			// ������Ϸ״̬
			isPlaying = false;
		}
	}

	// �����Ի���
	private AlertDialog.Builder createDialog(String title, String message,
			int imageResource) {
		return new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).setIcon(imageResource);
	}

	// ֹͣ��ʱ
	private void stopTimer() {
		this.timer.cancel();
		this.timer = null;
	}
}