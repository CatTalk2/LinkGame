package org.lol.link.board.impl;

import java.util.ArrayList;
import java.util.List;

import org.lol.link.board.AbstractBoard;
import org.lol.link.object.GameConf;
import org.lol.link.view.Piece;

/**
 * �������е���Ϸ����ʵ����
 */
public class VerticalBoard extends AbstractBoard {
	protected List<Piece> createPieces(GameConf config, Piece[][] pieces) {
		// ����һ��Piece����, �ü��������ų�ʼ����Ϸʱ�����Piece����
		List<Piece> notNullPieces = new ArrayList<Piece>();
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				// �����ж�, ����һ��������ȥ����Piece����, ���ӵ�������
				if (i % 2 == 0) {
					// ���x�ܱ�2����, �������в��ᴴ������
					Piece piece = new Piece(i, j);
					// ��ӵ�Piece������
					notNullPieces.add(piece);
				}
			}
		}
		return notNullPieces;
	}
}
