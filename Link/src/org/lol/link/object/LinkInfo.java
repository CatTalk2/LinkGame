package org.lol.link.object;

import java.util.List;
import java.util.ArrayList;

import android.graphics.Point;

/**
 * LinkInfo,������Ϣ�Ĵ����࣬�˴�����������������㷨������Դ
 */
public class LinkInfo {
	// ����һ���������ڱ������ӵ�
	private List<Point> points = new ArrayList<Point>();

	// �ṩ��һ��������, ��ʾ����Point����ֱ������, û��ת�۵�
	public LinkInfo(Point p1, Point p2) {
		// �ӵ�������ȥ
		points.add(p1);
		points.add(p2);
	}

	// �ṩ�ڶ���������, ��ʾ����Point��������, p2��p1��p3֮���ת�۵�
	public LinkInfo(Point p1, Point p2, Point p3) {
		points.add(p1);
		points.add(p2);
		points.add(p3);
	}

	// �ṩ������������, ��ʾ�ĸ�Point��������, p2, p3��p1��p4��ת�۵�
	public LinkInfo(Point p1, Point p2, Point p3, Point p4) {
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
	}

	// �������Ӽ���
	public List<Point> getLinkPoints() {
		return points;
	}
}
