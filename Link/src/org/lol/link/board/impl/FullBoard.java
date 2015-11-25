package org.lol.link.board.impl;

import java.util.ArrayList;
import java.util.List;

import org.lol.link.board.AbstractBoard;
import org.lol.link.object.GameConf;
import org.lol.link.view.Piece;

/**
 * ������Ϸ�����ʵ����
 */
public class FullBoard extends AbstractBoard {
	@Override
	protected List<Piece> createPieces(GameConf config, Piece[][] pieces) {
		// ����һ��Piece����, �ü��������ų�ʼ����Ϸʱ�����Piece����
		List<Piece> notNullPieces = new ArrayList<Piece>();
		for (int i = 1; i < pieces.length - 1; i++) {
			for (int j = 1; j < pieces[i].length - 1; j++) {
				Piece piece = new Piece(i, j);
				// ��ӵ�Piece������
				notNullPieces.add(piece);
			}
		}
		return notNullPieces;
	}
}
