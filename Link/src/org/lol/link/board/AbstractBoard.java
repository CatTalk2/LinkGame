package org.lol.link.board;

import java.util.List;

import org.lol.link.object.GameConf;
import org.lol.link.util.ImageUtil;
import org.lol.link.view.Piece;
import org.lol.link.view.PieceImage;

/**
 * ��Ϸ�����ʵ�֣�����ѡ�ó����࣬������ȥʵ�ֺ������У��������к;�������
 */
public abstract class AbstractBoard {
	// ����һ�����󷽷�, ������ȥʵ�֣��˴������˴������ϣ�������
	protected abstract List<Piece> createPieces(GameConf config,
			Piece[][] pieces);

	public Piece[][] create(GameConf config) {
		// ����Piece[][]����
		Piece[][] pieces = new Piece[config.getXSize()][config.getYSize()];
		List<Piece> notNullPieces = createPieces(config, pieces);
		List<PieceImage> playImages = ImageUtil.getPlayImages(
				config.getContext(), notNullPieces.size());
		// ����ͼƬ�Ŀ��߶�����ͬ��
		int imageWidth = playImages.get(0).getImage().getWidth();
		int imageHeight = playImages.get(0).getImage().getHeight();

		for (int i = 0; i < notNullPieces.size(); i++) {

			Piece piece = notNullPieces.get(i);
			piece.setImage(playImages.get(i));
			// ����ÿ���������Ͻǵ�X��Y����
			piece.setBeginX(piece.getIndexX() * imageWidth
					+ config.getBeginImageX());
			piece.setBeginY(piece.getIndexY() * imageHeight
					+ config.getBeginImageY());
			// ���÷��������뷽���������Ӧλ�ô�
			pieces[piece.getIndexX()][piece.getIndexY()] = piece;
		}
		return pieces;
	}
}
