package com.attendanceApp.projections;

public interface UserProjection {

     Long getId();
     String getEmail();
     String getFullName();
     String getRole();
     Boolean getIsActive();

}
