package org.lol.link.board;

import org.lol.link.object.LinkInfo;
import org.lol.link.view.Piece;

/**
 * ������Ҫ����Ϸ�߼��ӿ�
 */
public interface GameService {
	// ������Ϸ��ʼ�ķ���
	void start();

	// ����һ���ӿڷ���, ���ڷ���һ����ά����@return ��ŷ������Ķ�ά����
	Piece[][] getPieces();

	// �жϲ���Piece[][]�������Ƿ񻹴��ڷǿյ�Piece����@return �����ʣPiece���󷵻�true, û�з���false
	boolean hasPieces();

	// �������X��Y����
	Piece findPiece(float touchX, float touchY);

	// �ж�����Piece�Ƿ��������
	LinkInfo link(Piece p1, Piece p2);
}
