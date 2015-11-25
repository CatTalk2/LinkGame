//�������ֲ��ŵ�ʵ��
package org.lol.link;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
	private MediaPlayer mp;

	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		// ��ʼ��������
		mp.start();
		// ���ֲ�����ϵ��¼�����
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				// ѭ������
				try {
					mp.start();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// ��������ʱ����������¼�����
		mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {

			public boolean onError(MediaPlayer mp, int what, int extra) {
				// TODO Auto-generated method stub
				// �ͷ���Դ
				try {
					mp.release();
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		});

		super.onStart(intent, startId);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		// ��ʼ��������Դ
		try {
			// ����MediaPlayer����
			mp = new MediaPlayer();
			// �����ֱ�����res/raw/lol.mp3,R.java���Զ�����id
			mp = MediaPlayer.create(MusicService.this, R.raw.lol);
			// ��MediaPlayerȡ�ò�����Դ��stop()֮��Ҫ׼��PlayBack��״̬ǰһ��Ҫʹ��MediaPlayer.prepeare()
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		// ����ֹͣʱֹͣ�������ֲ��ͷ���Դ
		mp.stop();
		mp.release();

		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
