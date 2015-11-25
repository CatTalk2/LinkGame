package org.lol.link.view;

import android.graphics.Point;

/**
 * ��������Ϸ���򷽿����Ĵ�����
 */
public class Piece {
	// ���淽����������Ӧ��ͼƬ
	private PieceImage image;
	// �÷���ͼ������Ͻǵ�x����
	private int beginX;
	// �÷���ͼ������Ͻǵ�y����
	private int beginY;

	private int indexX;

	private int indexY;

	public Piece(int indexX, int indexY) {
		this.indexX = indexX;
		this.indexY = indexY;
	}

	public int getBeginX() {
		return beginX;
	}

	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}

	public int getBeginY() {
		return beginY;
	}

	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}

	public int getIndexX() {
		return indexX;
	}

	public void setIndexX(int indexX) {
		this.indexX = indexX;
	}

	public int getIndexY() {
		return indexY;
	}

	public void setIndexY(int indexY) {
		this.indexY = indexY;
	}

	public PieceImage getImage() {
		return image;
	}

	public void setImage(PieceImage image) {
		this.image = image;
	}

	// ��ȡ��Piece������
	public Point getCenter() {
		return new Point(getImage().getImage().getWidth() / 2 + getBeginX(),
				getBeginY() + getImage().getImage().getHeight() / 2);
	}

	// �ж�����ͼƬ�Ƿ���ͬ
	public boolean isSameImage(Piece other) {
		if (image == null) {
			if (other.image != null)
				return false;
		}
		// ֻҪͼƬID��ͬ��������Ϊ����Piece��ȡ�
		return image.getImageId() == other.image.getImageId();
	}
}
