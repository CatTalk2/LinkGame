package org.lol.link.object;

import android.content.Context;

/**
 * �ṩһ��������������������������ֵ�������й���
 */
public class GameConf {
	// ������������ÿ�������ͼƬ�Ŀ���
	public static final int PIECE_WIDTH = 40;
	public static final int PIECE_HEIGHT = 40;
	// ��¼��Ϸ����ʱ�䣨60�룩.
	public static int DEFAULT_TIME = 60;
	// Piece[][]�����һά�ĳ���
	private int xSize;
	// Piece[][]����ڶ�ά�ĳ���
	private int ySize;
	// ��Ϸ�����һ��ͼƬ���ֵ�x����
	private int beginImageX;
	// ��Ϸ�����е�һ��ͼƬ���ֵ�y����
	private int beginImageY;
	// ��¼��Ϸ����ʱ��, ��λ�Ǻ���
	private long gameTime;
	private Context context;

	public GameConf(int xSize, int ySize, int beginImageX, int beginImageY,
			long gameTime, Context context) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.beginImageX = beginImageX;
		this.beginImageY = beginImageY;
		this.gameTime = gameTime;
		this.context = context;
	}

	public long getGameTime() {
		return gameTime;
	}

	public int getXSize() {
		return xSize;
	}

	public int getYSize() {
		return ySize;
	}

	public int getBeginImageX() {
		return beginImageX;
	}

	public int getBeginImageY() {
		return beginImageY;
	}

	public Context getContext() {
		return context;
	}
}
