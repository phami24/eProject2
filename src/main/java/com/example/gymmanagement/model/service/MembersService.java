package com.example.gymmanagement.model.service;

import com.example.gymmanagement.model.entity.Members;

import java.util.List;

public interface MembersService {
    void addMember(Members member);
    void updateMember(Members member);
    void deleteMember(int memberId);
    Members getMemberById(int memberId);
    List<Members> getAllMembers();
    int getTotalMembers();
    List<Members> getMembersByNames(String firstName, String lastName);
    int getNumberOfMembersByInstructorId(int instructorId);
    List<Members> searchMembersByName(String name);
}
