package com.bootdo.oa.service.impl;

import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.oa.dao.FjDao;
import com.bootdo.oa.domain.FjDO;
import com.bootdo.oa.service.FjService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class FjServiceImpl implements FjService {
	@Autowired
	private FjDao fjDao;

	@Value("${bootdo.uploadPath}")
	private String uploadPath;
	
	@Override
	public FjDO get(String id){
		return fjDao.get(id);
	}
	
	@Override
	public List<FjDO> list(Map<String, Object> map){
		return fjDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fjDao.count(map);
	}
	
	@Override
	public int save(FjDO fj){
		fj.setId(UUIDUtils.randomUUID());
		return fjDao.save(fj);
	}
	
	@Override
	public int update(FjDO fj){
		return fjDao.update(fj);
	}
	
	@Override
	public int remove(String id){
		return fjDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return fjDao.batchRemove(ids);
	}

	@Override
	public int deleteByJcxxId(String jcxxId) {
		//删除数据库的同时把对面本地文件夹的图片删除
		//先查出图片本地路径
		FjDO fj = fjDao.getByJcxxId(jcxxId);
		//删除图片
		if (!StringUtil.isNullOrEmpty(fj.getStudyImg())){
			FileUtil.deleteFile(fj.getStudyImg());
		}
		if (!StringUtil.isNullOrEmpty(fj.getDegreeImg())){
			FileUtil.deleteFile(fj.getDegreeImg());
		}
		if (!StringUtil.isNullOrEmpty(fj.getCardImgF())){
			FileUtil.deleteFile(fj.getCardImgF());
		}
		if (!StringUtil.isNullOrEmpty(fj.getCardImgR())){
			FileUtil.deleteFile(fj.getCardImgR());
		}

		return fjDao.deleteByJcxxId(jcxxId);
	}

	@Override
	public void filesUpload(MultipartFile[] files, String jcxxId) {
		FjDO fj = fjDao.getByJcxxId(jcxxId);
		for (MultipartFile file : files) {
			if (Objects.nonNull(file)) {
				try {
					if (file.getName().equals("毕业证")) {
						String studyImg = FileUtil.uploadFile(file.getBytes(), uploadPath, UUIDUtils.randomUUID());
						fj.setStudyImg(studyImg);
					} else if (file.getName().equals("学位证")) {
						String degreeImg = FileUtil.uploadFile(file.getBytes(), uploadPath, UUIDUtils.randomUUID());
						fj.setDegreeImg(degreeImg);
					} else if (file.getName().equals("身份证前面")) {
						String cardImgF = FileUtil.uploadFile(file.getBytes(), uploadPath, UUIDUtils.randomUUID());
						fj.setCardImgF(cardImgF);
					} else if (file.getName().equals("身份证反面")) {
						String cardImgR = FileUtil.uploadFile(file.getBytes(), uploadPath, UUIDUtils.randomUUID());
						fj.setCardImgR(cardImgR);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		update(fj);
	}

}
