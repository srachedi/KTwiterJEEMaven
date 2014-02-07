SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists Comment;

drop table if exists Member;

drop table if exists Post;

drop table if exists Profile;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists Comment_seq;

drop sequence if exists Member_seq;

drop sequence if exists Post_seq;

drop sequence if exists Profile_seq;

