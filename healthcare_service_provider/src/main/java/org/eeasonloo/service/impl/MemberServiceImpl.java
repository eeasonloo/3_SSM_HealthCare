package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.eeasonloo.dao.MemberDao;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.entity.RedisConstant;
import org.eeasonloo.pojo.Member;
import org.eeasonloo.pojo.Setmeal;
import org.eeasonloo.service.MemberService;
import org.eeasonloo.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public void add(Member member) {
        if(member.getPassword() != null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberDao.findByEmail(email);
    }

    //根据月份统计会员数量
    @Override
    public List<Integer> findMemberCountByMonth(List<String> month) {
        List<Integer> list = new ArrayList<Integer>();
        for(String m : month){
            m = m + ".31";//格式：2019.04.31
            Integer count = memberDao.findMemberCountBeforeDate(m);
            list.add(count);
        }
        return list;
    }

    @Override
    public PageResult findPage(int pageSize, int currentPage, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = memberDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Member findById(int id) {
        return memberDao.findById(id);
    }

    @Override
    public void delete(Integer id) {
        memberDao.deleteById(id);
    }

    @Override
    public void edit(Member member) {
        //1. update t_setmeal
        memberDao.edit(member);
    }


}
