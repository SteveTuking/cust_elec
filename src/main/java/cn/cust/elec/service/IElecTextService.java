package cn.cust.elec.service;

import java.util.List;

import cn.cust.elec.domain.ElecText;

public interface IElecTextService {
	public void saveElecText(ElecText elecText) throws Exception;

	public List<ElecText> findCollectionByConditionNoPage(ElecText elecText);

	public List<ElecText> findElecText();
}
