
    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKoa6p4s2oyy7tf80xwc4r04vh6`;

    alter table `application` 
       drop 
       foreign key `FKmbjdoxi3o93agxosoate4sxbt`;

    alter table `audit` 
       drop 
       foreign key `FK7x4vmrfrh2nyj9mwha7np1ab4`;

    alter table `audit` 
       drop 
       foreign key `FKijp0sxquetnc9erybuvwrg2e4`;

    alter table `auditor` 
       drop 
       foreign key FK_clqcq9lyspxdxcp6o4f3vkelj;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `banner` 
       drop 
       foreign key `FKjoxwdnjr54soq3j89kt3fgrtj`;

    alter table `commercial_banner` 
       drop 
       foreign key FK_q9id3wc65gg49afc5tlr1c00n;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `duty` 
       drop 
       foreign key `FKs2uoxh4i5ya8ptyefae60iao1`;

    alter table `employer` 
       drop 
       foreign key FK_na4dfobmeuxkwf6p75abmb2tr;

    alter table `job` 
       drop 
       foreign key `FK3rxjf8uh6fh2u990pe8i2at0e`;

    alter table `message` 
       drop 
       foreign key `FK28hjkn063wrsjuiyyf8sm3s2v`;

    alter table `message` 
       drop 
       foreign key `FKik4epe9dp5q6uenarfyia7xin`;

    alter table `non_commercial_banner` 
       drop 
       foreign key FK_2l8gpcwh19e7jj513or4r9dvb;

    alter table `participant` 
       drop 
       foreign key `FK8mocuiguc2q27excsu2w2q9ai`;

    alter table `participant` 
       drop 
       foreign key `FK67h73ib586xy9hvw4vyy75fvv`;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `sponsor` 
       drop 
       foreign key FK_20xk0ev32hlg96kqynl6laie2;

    alter table `worker` 
       drop 
       foreign key FK_l5q1f33vs2drypmbdhpdgwfv3;

    drop table if exists `administrator`;

    drop table if exists `announcement`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `audit`;

    drop table if exists `auditor`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `challenge`;

    drop table if exists `commercial_banner`;

    drop table if exists `company`;

    drop table if exists `consumer`;

    drop table if exists `customization`;

    drop table if exists `duty`;

    drop table if exists `employer`;

    drop table if exists `investor`;

    drop table if exists `job`;

    drop table if exists `message`;

    drop table if exists `non_commercial_banner`;

    drop table if exists `offer`;

    drop table if exists `participant`;

    drop table if exists `provider`;

    drop table if exists `shout`;

    drop table if exists `solicitud`;

    drop table if exists `sponsor`;

    drop table if exists `thread`;

    drop table if exists `user_account`;

    drop table if exists `worker`;

    drop table if exists `hibernate_sequence`;
