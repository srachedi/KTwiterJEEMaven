create table Comment (
  id                        bigint not null,
  like_comment              bigint,
  content                   varchar(255),
  post_id                   bigint,
  autor_id                  bigint,
  comment_date              timestamp,
  constraint pk_Comment primary key (id))
;

create table Member (
  id                        bigint not null,
  login                     varchar(255),
  email                     varchar(255),
  mot_passe                 varchar(255),
  constraint pk_Member primary key (id))
;

create table Post (
  id                        bigint not null,
  like_post                 bigint,
  title                     varchar(255),
  content                   varchar(255),
  post_date                 timestamp,
  autor_id                  bigint,
  constraint pk_Post primary key (id))
;

create table Profile (
  id                        bigint not null,
  nom                       varchar(255),
  prenom                    varchar(255),
  sexe                      varchar(255),
  member_id                 bigint,
  role                      varchar(255),
  date_naissance            timestamp,
  date_inscription          timestamp,
  constraint pk_Profile primary key (id))
;

create sequence Comment_seq;

create sequence Member_seq;

create sequence Post_seq;

create sequence Profile_seq;

alter table Comment add constraint fk_Comment_post_1 foreign key (post_id) references Post (id) on delete restrict on update restrict;
create index ix_Comment_post_1 on Comment (post_id);
alter table Comment add constraint fk_Comment_autor_2 foreign key (autor_id) references Member (id) on delete restrict on update restrict;
create index ix_Comment_autor_2 on Comment (autor_id);
alter table Post add constraint fk_Post_autor_3 foreign key (autor_id) references Member (id) on delete restrict on update restrict;
create index ix_Post_autor_3 on Post (autor_id);
alter table Profile add constraint fk_Profile_member_4 foreign key (member_id) references Member (id) on delete restrict on update restrict;
create index ix_Profile_member_4 on Profile (member_id);


