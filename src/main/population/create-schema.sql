
    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `announcement` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `more_info` varchar(255),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `answer` varchar(255),
        `justification` varchar(255),
        `moment` datetime(6),
        `qualifications` varchar(255),
        `reference` varchar(255),
        `skills` varchar(255),
        `statement` varchar(255),
        `status` varchar(255),
        `xxx4` varchar(255),
        `job_id` integer not null,
        `worker_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `audit` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `final_mode` bit not null,
        `moment` datetime(6),
        `title` varchar(255),
        `auditor_id` integer not null,
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authorized` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `accepted` bit not null,
        `body` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `url` varchar(255),
        `sponsor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `bronze_goal` varchar(255),
        `bronze_reward_amount` double precision,
        `bronze_reward_currency` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `gold_goal` varchar(255),
        `gold_reward_amount` double precision,
        `gold_reward_currency` varchar(255),
        `silver_goal` varchar(255),
        `silver_reward_amount` double precision,
        `silver_reward_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `commercial_banner` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `url` varchar(255),
        `sponsor_id` integer not null,
        `credit_card` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `company` (
       `id` integer not null,
        `version` integer not null,
        `activities` varchar(255),
        `ceo` varchar(255),
        `email` varchar(255),
        `incorporated` bit,
        `name` varchar(255),
        `phone` varchar(255),
        `sector` varchar(255),
        `stars` double precision,
        `url` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `customization` (
       `id` integer not null,
        `version` integer not null,
        `spam` varchar(255),
        `threshold` double precision,
        primary key (`id`)
    ) engine=InnoDB;

    create table `dare` (
       `id` integer not null,
        `version` integer not null,
        `more_info` varchar(255),
        `password` varchar(255),
        `text` varchar(255),
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `duty` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `time_amount` double precision,
        `title` varchar(255),
        `job_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `employer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `name` varchar(255),
        `sector` varchar(255),
        `stars` double precision,
        `statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `job` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(255),
        `final_mode` bit not null,
        `more_info` varchar(255),
        `reference_number` varchar(255),
        `salary_amount` double precision,
        `salary_currency` varchar(255),
        `title` varchar(255),
        `employer_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `thread_id` integer not null,
        `user_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `non_commercial_banner` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `url` varchar(255),
        `sponsor_id` integer not null,
        `jingle` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `offer` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `max_amount` double precision,
        `max_currency` varchar(255),
        `min_amount` double precision,
        `min_currency` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `participant` (
       `id` integer not null,
        `version` integer not null,
        `thread_id` integer not null,
        `user_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `shout` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `solicitud` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `moment` datetime(6),
        `reward_amount` double precision,
        `reward_currency` varchar(255),
        `text` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `sponsor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `credit_card` varchar(255),
        `organization` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `thread` (
       `id` integer not null,
        `version` integer not null,
        `creation_date` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `worker` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `qualifications` varchar(255),
        `skills` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXnhikaa2dj3la6o2o7e9vo01y0 on `announcement` (`moment`);
create index IDX2q2747fhp099wkn3j2yt05fhs on `application` (`status` asc);
create index IDXg54pxa1gngqheaipukeg8jypk on `application` (`moment` asc);

    alter table `application` 
       add constraint UK_ct7r18vvxl5g4c4k7aefpa4do unique (`reference`);
create index IDXoqmmqbafwj4m9wgwqexcmckjs on `audit` (`final_mode`);
create index IDXnr284tes3x8hnd3h716tmb3fr on `challenge` (`deadline`);
create index IDX3vwg77973akwy9ilnfq707yt1 on `company` (`stars`);
create index IDXbm7mwffwxwiukjmbmt9t1qnnu on `company` (`sector`);
create index IDXsxt70v3kwa209teqy90roxr8f on `dare` (`password`);

    alter table `dare` 
       add constraint UK_nllnej8asaxxvuewy1j0jp2te unique (`job_id`);
create index IDXq1q335kxox0leg1u9hhndvue1 on `investor` (`stars`);
create index IDX1slmmcr1g0wv9jbgun6rny0oy on `investor` (`sector`);
create index IDXfdmpnr8o4phmk81sqsano16r on `job` (`deadline`);
create index IDXt84ibbldao4ngscmvo7ja0es on `job` (`final_mode`);

    alter table `job` 
       add constraint UK_bos0omdc9s5vykasqjhwaq65m unique (`reference_number`);
create index IDXq2o9psuqfuqmq59f0sq57x9uf on `offer` (`deadline`);
create index IDXcp4664f36sgqsd0ihmirt0w0 on `offer` (`ticker`);

    alter table `offer` 
       add constraint UK_iex7e8fs0fh89yxpcnm1orjkm unique (`ticker`);
create index IDXd37rv1cyj5dvbd63k6progy7q on `solicitud` (`deadline`);
create index IDX2qy5jkiqwk6f13kkfq8pu61le on `solicitud` (`ticker`);

    alter table `solicitud` 
       add constraint UK_rea5aivw0b4fiu93s509u9lky unique (`ticker`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKoa6p4s2oyy7tf80xwc4r04vh6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `application` 
       add constraint `FKmbjdoxi3o93agxosoate4sxbt` 
       foreign key (`worker_id`) 
       references `worker` (`id`);

    alter table `audit` 
       add constraint `FK7x4vmrfrh2nyj9mwha7np1ab4` 
       foreign key (`auditor_id`) 
       references `auditor` (`id`);

    alter table `audit` 
       add constraint `FKijp0sxquetnc9erybuvwrg2e4` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `auditor` 
       add constraint FK_clqcq9lyspxdxcp6o4f3vkelj 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authorized` 
       add constraint FK_keqakltjauwrbk13pgunccany 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `banner` 
       add constraint `FKjoxwdnjr54soq3j89kt3fgrtj` 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `commercial_banner` 
       add constraint FK_q9id3wc65gg49afc5tlr1c00n 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `dare` 
       add constraint `FK1ygh5uri5q2aqmfvarh2gf1to` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `duty` 
       add constraint `FKs2uoxh4i5ya8ptyefae60iao1` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `employer` 
       add constraint FK_na4dfobmeuxkwf6p75abmb2tr 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `job` 
       add constraint `FK3rxjf8uh6fh2u990pe8i2at0e` 
       foreign key (`employer_id`) 
       references `employer` (`id`);

    alter table `message` 
       add constraint `FK28hjkn063wrsjuiyyf8sm3s2v` 
       foreign key (`thread_id`) 
       references `thread` (`id`);

    alter table `message` 
       add constraint `FKik4epe9dp5q6uenarfyia7xin` 
       foreign key (`user_id`) 
       references `authenticated` (`id`);

    alter table `non_commercial_banner` 
       add constraint FK_2l8gpcwh19e7jj513or4r9dvb 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `participant` 
       add constraint `FK8mocuiguc2q27excsu2w2q9ai` 
       foreign key (`thread_id`) 
       references `thread` (`id`);

    alter table `participant` 
       add constraint `FK67h73ib586xy9hvw4vyy75fvv` 
       foreign key (`user_id`) 
       references `authenticated` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `sponsor` 
       add constraint FK_20xk0ev32hlg96kqynl6laie2 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `worker` 
       add constraint FK_l5q1f33vs2drypmbdhpdgwfv3 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
