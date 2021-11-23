package org.eeasonloo.dao;

import com.github.pagehelper.Page;
import org.eeasonloo.pojo.Member;
import org.eeasonloo.pojo.Setmeal;

import java.util.List;

public interface MemberDao {
    public List<Member> findAll();
    public Page<Member> selectByCondition(String queryString);
    public void add(Member member);
    public void deleteById(Integer id);
    public Member findById(Integer id);
    public Member findByEmail(String email);
    public void edit(Member member);
    public Integer findMemberCountBeforeDate(String date);
    public Integer findMemberCountByDate(String date);
    public Integer findMemberCountAfterDate(String date);
    public Integer findMemberTotalCount();

    public Page<Setmeal> findByCondition(String queryString);

}
