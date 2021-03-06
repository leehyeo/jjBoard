package com.jjboard.mvc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jjboard.mvc.dao.BoardDao;
import com.jjboard.mvc.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {
	@Resource
	private BoardDao boardDao;

	public BoardDao getBoardDao() {
		return boardDao;
	}

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public List<Board> list() {
		return boardDao.findAll();
	}

	@Override
	public boolean delete(Board board) {
		Board vo = boardDao.findOne(board.getSeq());

		if (vo.getPassword() == board.getPassword()) {
			boardDao.delete(board);
			return true;
		}
		return false;
	}

	@Override
	public Board edit(Board board) {
		return boardDao.save(board);
	}

	@Override
	public Board write(Board board) {
		return boardDao.save(board);
	}

	@Override
	public Board read(int seq) {
		Board board = boardDao.findOne(seq);
		board.setCnt(board.getCnt() + 1);
		boardDao.save(board);
		return board;
	}

}
