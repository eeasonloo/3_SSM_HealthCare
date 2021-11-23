package org.eeasonloo.service;

import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.Member;
import org.eeasonloo.pojo.Setmeal;

import java.util.List;

public interface MemberService {

    public void add(Member member);
    public void edit(Member member);

    public Member findByEmail(String email);

    public List<Integer> findMemberCountByMonth(List<String> month);

    public PageResult findPage(int pageSize, int currentPage, String queryString);

    public Member findById(int id);

    public void delete(Integer id);


}
