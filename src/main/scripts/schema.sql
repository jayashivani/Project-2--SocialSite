
    alter table ACTIVATION 
        drop 
        foreign key fk_Activation_admin_id_Admin_id;

    alter table ADMIN 
        drop 
        foreign key FK3B40B2FE8BA0282;

    alter table ANSWER 
        drop 
        foreign key fk_Answer_user_id_User_id;

    alter table ANSWER 
        drop 
        foreign key fk_Answer_question_id_Question_id;

    alter table COMMENT 
        drop 
        foreign key fk_Comment_user_id_User_id;

    alter table COMMENT 
        drop 
        foreign key fk_Comment_answer_id_Answer_id;

    alter table COURSE 
        drop 
        foreign key fk_Course_university_id_University_id;

    alter table COURSE 
        drop 
        foreign key fk_Course_staff_id_Staff_id;

    alter table COURSE_JOINED_MSG 
        drop 
        foreign key fk_COURSE_JOINED_MSG_course_id_Course_id;

    alter table COURSE_JOINED_MSG 
        drop 
        foreign key FK24F63C0FE1E64E06;

    alter table COURSE_JOINED_MSG 
        drop 
        foreign key fk_COURSE_JOINED_MSG_sender_id_User_id;

    alter table COURSE_NOTE_MSG 
        drop 
        foreign key fk_COURSE_NOTE_MSG_note_id_Note_id;

    alter table COURSE_NOTE_MSG 
        drop 
        foreign key FK90948538E1E64E06;

    alter table FRIEND_REQUEST_MSG 
        drop 
        foreign key FKED4A6970E1E64E06;

    alter table FRIEND_REQUEST_MSG 
        drop 
        foreign key fk_FRIEND_REQUEST_MSG_sender_id_User_id;

    alter table INFO_MSG 
        drop 
        foreign key FK3954B050E1E64E06;

    alter table INFO_MSG 
        drop 
        foreign key fk_INFO_MSG_sender_id_User_id;

    alter table NOTE 
        drop 
        foreign key fk_Note_course_id_Course_id;

    alter table Profile 
        drop 
        foreign key fk_Profile_user_id_User_id;

    alter table QUESTION 
        drop 
        foreign key fk_Question_course_id_Course_id;

    alter table QUESTION 
        drop 
        foreign key fk_Question_user_id_User_id;

    alter table QUESTION_INFO_MSG 
        drop 
        foreign key FKF28DB889E1E64E06;

    alter table QUESTION_INFO_MSG 
        drop 
        foreign key fk_Question_QuestionInfoMsg_id_question_id;

    alter table STAFF 
        drop 
        foreign key fk_Staff_university_id_University_id;

    alter table STAFF 
        drop 
        foreign key FK4B8CAC0E8BA0282;

    alter table STAFF_REQUEST_MSG 
        drop 
        foreign key fk_STAFF_REQUEST_MSG_university_id_University_id;

    alter table STAFF_REQUEST_MSG 
        drop 
        foreign key FK95FF1292E1E64E06;

    alter table STAFF_REQUEST_MSG 
        drop 
        foreign key fk_STAFF_REQUEST_MSG_sender_id_Staff_id;

    alter table STUDENT 
        drop 
        foreign key FKBACA0E1BE8BA0282;

    alter table STUDENT_COURSE 
        drop 
        foreign key fk_STUDENT_COURSE_course_id_Course_id;

    alter table STUDENT_COURSE 
        drop 
        foreign key fk_STUDENT_COURSE_student_id_Student_id;

    alter table UNIVERSITY 
        drop 
        foreign key fk_University_admin_id_Admin_id;

    alter table friend_reference 
        drop 
        foreign key fk_Friend_Reference_friend_id_User_id;

    alter table friend_reference 
        drop 
        foreign key fk_Friend_Reference_user_id_User_id;

    alter table message_user 
        drop 
        foreign key message_user_user_id_User_id;

    alter table message_user 
        drop 
        foreign key message_user_message_id_Message_id;

    drop table if exists ACTIVATION;

    drop table if exists ADMIN;

    drop table if exists ANSWER;

    drop table if exists COMMENT;

    drop table if exists COURSE;

    drop table if exists COURSE_JOINED_MSG;

    drop table if exists COURSE_NOTE_MSG;

    drop table if exists FRIEND_REQUEST_MSG;

    drop table if exists INFO_MSG;

    drop table if exists MESSAGE;

    drop table if exists NOTE;

    drop table if exists Profile;

    drop table if exists QUESTION;

    drop table if exists QUESTION_INFO_MSG;

    drop table if exists STAFF;

    drop table if exists STAFF_REQUEST_MSG;

    drop table if exists STUDENT;

    drop table if exists STUDENT_COURSE;

    drop table if exists UNIVERSITY;

    drop table if exists User;

    drop table if exists friend_reference;

    drop table if exists message_user;

    create table ACTIVATION (
        id bigint not null auto_increment,
        universityName varchar(255),
        admin_id bigint,
        primary key (id)
    );

    create table ADMIN (
        id bigint not null,
        primary key (id)
    );

    create table ANSWER (
        id bigint not null auto_increment,
        time datetime,
        text text,
        user_id bigint,
        question_id bigint,
        primary key (id)
    );

    create table COMMENT (
        id bigint not null auto_increment,
        time datetime,
        text text,
        answer_id bigint,
        user_id bigint,
        primary key (id)
    );

    create table COURSE (
        id bigint not null auto_increment,
        name varchar(255),
        description text,
        image mediumblob,
        thumb mediumblob,
        lastModified datetime,
        university_id bigint,
        staff_id bigint,
        primary key (id)
    );

    create table COURSE_JOINED_MSG (
        id bigint not null,
        sender_id bigint,
        course_id bigint,
        primary key (id)
    );

    create table COURSE_NOTE_MSG (
        id bigint not null,
        note_id bigint,
        primary key (id)
    );

    create table FRIEND_REQUEST_MSG (
        id bigint not null,
        message varchar(255),
        sender_id bigint,
        primary key (id)
    );

    create table INFO_MSG (
        id bigint not null,
        message text,
        sender_id bigint,
        primary key (id)
    );

    create table MESSAGE (
        id bigint not null auto_increment,
        time datetime,
        primary key (id)
    );

    create table NOTE (
        id bigint not null auto_increment,
        description text,
        time datetime,
        fileName varchar(255),
        contentType varchar(255),
        data mediumblob,
        course_id bigint,
        primary key (id)
    );

    create table Profile (
        user_id bigint not null,
        firstName varchar(255),
        lastName varchar(255),
        email varchar(255),
        sex varchar(255),
        currentCity_value varchar(255),
        currentCity_privacy varchar(255),
        homeTown_value varchar(255),
        homeTown_privacy varchar(255),
        relationshipStatus varchar(255),
        politicalView text,
        religiousView text,
        activities text,
        interests text,
        favouriteMusic text,
        favouriteMovies text,
        favouriteTvShows text,
        favouriteQuotations text,
        favouriteBooks text,
        aboutMe text,
        mobilePhone_value varchar(255),
        mobilePhone_privacy varchar(255),
        landPhone_value varchar(255),
        landPhone_privacy varchar(255),
        address_value varchar(255),
        address_privacy varchar(255),
        city varchar(255),
        neighborhood varchar(255),
        zip integer,
        website varchar(255),
        college varchar(255),
        image mediumblob,
        thumb mediumblob,
        primary key (user_id)
    );

    create table QUESTION (
        id bigint not null auto_increment,
        heading varchar(255),
        time datetime,
        text text,
        course_id bigint,
        user_id bigint,
        primary key (id)
    );

    create table QUESTION_INFO_MSG (
        id bigint not null,
        question_id bigint,
        primary key (id)
    );

    create table STAFF (
        id bigint not null,
        university_id bigint,
        primary key (id)
    );

    create table STAFF_REQUEST_MSG (
        id bigint not null,
        sender_id bigint,
        university_id bigint,
        primary key (id)
    );

    create table STUDENT (
        id bigint not null,
        primary key (id)
    );

    create table STUDENT_COURSE (
        student_id bigint not null,
        course_id bigint not null,
        primary key (course_id, student_id)
    );

    create table UNIVERSITY (
        id bigint not null auto_increment,
        name varchar(255),
        image mediumblob,
        thumb mediumblob,
        lastModified datetime,
        admin_id bigint,
        primary key (id)
    );

    create table User (
        id bigint not null auto_increment,
        userName varchar(255) not null unique,
        password varchar(255) not null,
        lastModified datetime,
        primary key (id)
    );

    create table friend_reference (
        friend_id bigint not null,
        user_id bigint not null,
        primary key (friend_id, user_id)
    );

    create table message_user (
        user_id bigint not null,
        message_id bigint not null,
        primary key (message_id, user_id)
    );

    alter table ACTIVATION 
        add index fk_Activation_admin_id_Admin_id (admin_id), 
        add constraint fk_Activation_admin_id_Admin_id 
        foreign key (admin_id) 
        references ADMIN (id);

    alter table ADMIN 
        add index FK3B40B2FE8BA0282 (id), 
        add constraint FK3B40B2FE8BA0282 
        foreign key (id) 
        references User (id);

    alter table ANSWER 
        add index fk_Answer_user_id_User_id (user_id), 
        add constraint fk_Answer_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table ANSWER 
        add index fk_Answer_question_id_Question_id (question_id), 
        add constraint fk_Answer_question_id_Question_id 
        foreign key (question_id) 
        references QUESTION (id);

    alter table COMMENT 
        add index fk_Comment_user_id_User_id (user_id), 
        add constraint fk_Comment_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table COMMENT 
        add index fk_Comment_answer_id_Answer_id (answer_id), 
        add constraint fk_Comment_answer_id_Answer_id 
        foreign key (answer_id) 
        references ANSWER (id);

    alter table COURSE 
        add index fk_Course_university_id_University_id (university_id), 
        add constraint fk_Course_university_id_University_id 
        foreign key (university_id) 
        references UNIVERSITY (id);

    alter table COURSE 
        add index fk_Course_staff_id_Staff_id (staff_id), 
        add constraint fk_Course_staff_id_Staff_id 
        foreign key (staff_id) 
        references STAFF (id);

    alter table COURSE_JOINED_MSG 
        add index fk_COURSE_JOINED_MSG_course_id_Course_id (course_id), 
        add constraint fk_COURSE_JOINED_MSG_course_id_Course_id 
        foreign key (course_id) 
        references COURSE (id);

    alter table COURSE_JOINED_MSG 
        add index FK24F63C0FE1E64E06 (id), 
        add constraint FK24F63C0FE1E64E06 
        foreign key (id) 
        references MESSAGE (id);

    alter table COURSE_JOINED_MSG 
        add index fk_COURSE_JOINED_MSG_sender_id_User_id (sender_id), 
        add constraint fk_COURSE_JOINED_MSG_sender_id_User_id 
        foreign key (sender_id) 
        references User (id);

    alter table COURSE_NOTE_MSG 
        add index fk_COURSE_NOTE_MSG_note_id_Note_id (note_id), 
        add constraint fk_COURSE_NOTE_MSG_note_id_Note_id 
        foreign key (note_id) 
        references NOTE (id);

    alter table COURSE_NOTE_MSG 
        add index FK90948538E1E64E06 (id), 
        add constraint FK90948538E1E64E06 
        foreign key (id) 
        references MESSAGE (id);

    alter table FRIEND_REQUEST_MSG 
        add index FKED4A6970E1E64E06 (id), 
        add constraint FKED4A6970E1E64E06 
        foreign key (id) 
        references MESSAGE (id);

    alter table FRIEND_REQUEST_MSG 
        add index fk_FRIEND_REQUEST_MSG_sender_id_User_id (sender_id), 
        add constraint fk_FRIEND_REQUEST_MSG_sender_id_User_id 
        foreign key (sender_id) 
        references User (id);

    alter table INFO_MSG 
        add index FK3954B050E1E64E06 (id), 
        add constraint FK3954B050E1E64E06 
        foreign key (id) 
        references MESSAGE (id);

    alter table INFO_MSG 
        add index fk_INFO_MSG_sender_id_User_id (sender_id), 
        add constraint fk_INFO_MSG_sender_id_User_id 
        foreign key (sender_id) 
        references User (id);

    alter table NOTE 
        add index fk_Note_course_id_Course_id (course_id), 
        add constraint fk_Note_course_id_Course_id 
        foreign key (course_id) 
        references COURSE (id);

    alter table Profile 
        add index fk_Profile_user_id_User_id (user_id), 
        add constraint fk_Profile_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table QUESTION 
        add index fk_Question_course_id_Course_id (course_id), 
        add constraint fk_Question_course_id_Course_id 
        foreign key (course_id) 
        references COURSE (id);

    alter table QUESTION 
        add index fk_Question_user_id_User_id (user_id), 
        add constraint fk_Question_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table QUESTION_INFO_MSG 
        add index FKF28DB889E1E64E06 (id), 
        add constraint FKF28DB889E1E64E06 
        foreign key (id) 
        references MESSAGE (id);

    alter table QUESTION_INFO_MSG 
        add index fk_Question_QuestionInfoMsg_id_question_id (question_id), 
        add constraint fk_Question_QuestionInfoMsg_id_question_id 
        foreign key (question_id) 
        references QUESTION (id);

    alter table STAFF 
        add index fk_Staff_university_id_University_id (university_id), 
        add constraint fk_Staff_university_id_University_id 
        foreign key (university_id) 
        references UNIVERSITY (id);

    alter table STAFF 
        add index FK4B8CAC0E8BA0282 (id), 
        add constraint FK4B8CAC0E8BA0282 
        foreign key (id) 
        references User (id);

    alter table STAFF_REQUEST_MSG 
        add index fk_STAFF_REQUEST_MSG_university_id_University_id (university_id), 
        add constraint fk_STAFF_REQUEST_MSG_university_id_University_id 
        foreign key (university_id) 
        references UNIVERSITY (id);

    alter table STAFF_REQUEST_MSG 
        add index FK95FF1292E1E64E06 (id), 
        add constraint FK95FF1292E1E64E06 
        foreign key (id) 
        references MESSAGE (id);

    alter table STAFF_REQUEST_MSG 
        add index fk_STAFF_REQUEST_MSG_sender_id_Staff_id (sender_id), 
        add constraint fk_STAFF_REQUEST_MSG_sender_id_Staff_id 
        foreign key (sender_id) 
        references STAFF (id);

    alter table STUDENT 
        add index FKBACA0E1BE8BA0282 (id), 
        add constraint FKBACA0E1BE8BA0282 
        foreign key (id) 
        references User (id);

    alter table STUDENT_COURSE 
        add index fk_STUDENT_COURSE_course_id_Course_id (course_id), 
        add constraint fk_STUDENT_COURSE_course_id_Course_id 
        foreign key (course_id) 
        references COURSE (id);

    alter table STUDENT_COURSE 
        add index fk_STUDENT_COURSE_student_id_Student_id (student_id), 
        add constraint fk_STUDENT_COURSE_student_id_Student_id 
        foreign key (student_id) 
        references STUDENT (id);

    alter table UNIVERSITY 
        add index fk_University_admin_id_Admin_id (admin_id), 
        add constraint fk_University_admin_id_Admin_id 
        foreign key (admin_id) 
        references ADMIN (id);

    alter table friend_reference 
        add index fk_Friend_Reference_friend_id_User_id (friend_id), 
        add constraint fk_Friend_Reference_friend_id_User_id 
        foreign key (friend_id) 
        references User (id);

    alter table friend_reference 
        add index fk_Friend_Reference_user_id_User_id (user_id), 
        add constraint fk_Friend_Reference_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table message_user 
        add index message_user_user_id_User_id (user_id), 
        add constraint message_user_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table message_user 
        add index message_user_message_id_Message_id (message_id), 
        add constraint message_user_message_id_Message_id 
        foreign key (message_id) 
        references MESSAGE (id);
