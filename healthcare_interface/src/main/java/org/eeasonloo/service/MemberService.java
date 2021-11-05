package org.eeasonloo.service;

import org.eeasonloo.pojo.Member;

import java.util.List;

public interface MemberService {

    public void add(Member member);
    public Member findByEmail(String email);

    public List<Integer> findMemberCountByMonth(List<String> month);
}
