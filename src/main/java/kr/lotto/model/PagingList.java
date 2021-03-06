package kr.lotto.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PagingList<T>
{

	private List<T>	listData	= null;

	private Paging	paging;

	public PagingList ()
	{
	}

	public PagingList (List<T> listData, Paging paging)
	{
		this.listData = listData;
		this.paging = paging;
	}
}
