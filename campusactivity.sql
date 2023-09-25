/*
 Navicat Premium Data Transfer

 Source Server         : mangement
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : campusactivity

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 25/09/2023 20:43:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for campus_admin
-- ----------------------------
DROP TABLE IF EXISTS `campus_admin`;
CREATE TABLE `campus_admin`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员表ID',
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员名称',
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员密码',
  `last_login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `openid` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '微信openid',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户手机号码',
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员邮箱',
  `gender` tinyint(4) NULL DEFAULT 0 COMMENT '性别：0 未知， 1男， 2 女',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像图片',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `role_ids` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '[]' COMMENT '角色列表',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '租户ID，用于分割多个租户',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '更新版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of campus_admin
-- ----------------------------

-- ----------------------------
-- Table structure for campus_advert
-- ----------------------------
DROP TABLE IF EXISTS `campus_advert`;
CREATE TABLE `campus_advert`  (
  `id` bigint(20) NOT NULL COMMENT '广告id',
  `fatherid` bigint(20) NOT NULL COMMENT '父级id，可能是post，也可能是评论，为0是广告',
  `user_id` bigint(20) NOT NULL COMMENT '回复的人的id',
  `replay_id` bigint(20) NOT NULL COMMENT '被回复的的人的id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '跳转链接',
  `photo_url` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '图片地址',
  `like` bigint(20) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `comment` bigint(20) NOT NULL DEFAULT 0 COMMENT '评论数',
  `collect` bigint(20) NOT NULL DEFAULT 0 COMMENT '收藏数',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '更新版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '广告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of campus_advert
-- ----------------------------

-- ----------------------------
-- Table structure for campus_collect
-- ----------------------------
DROP TABLE IF EXISTS `campus_collect`;
CREATE TABLE `campus_collect`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `post_id` bigint(255) NOT NULL COMMENT '图文id',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of campus_collect
-- ----------------------------

-- ----------------------------
-- Table structure for campus_comment
-- ----------------------------
DROP TABLE IF EXISTS `campus_comment`;
CREATE TABLE `campus_comment`  (
  `id` bigint(20) NOT NULL COMMENT '评论id',
  `fatherid` bigint(20) NOT NULL COMMENT '父级id，可能是post，也可能是评论',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '回复的人的id',
  `replay_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '被回复的人的id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `photo_url` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '图片地址',
  `liked` bigint(20) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `comment` bigint(20) NOT NULL DEFAULT 0 COMMENT '评论数',
  `collect` bigint(20) NOT NULL DEFAULT 0 COMMENT '收藏数',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '更新版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of campus_comment
-- ----------------------------

-- ----------------------------
-- Table structure for campus_interest_user
-- ----------------------------
DROP TABLE IF EXISTS `campus_interest_user`;
CREATE TABLE `campus_interest_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `interest_user_id` bigint(20) NOT NULL COMMENT '关注的用户',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`, `interest_user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of campus_interest_user
-- ----------------------------
INSERT INTO `campus_interest_user` VALUES (232627360170508290, 232544536021172240, '2023-09-23 16:04:10');

-- ----------------------------
-- Table structure for campus_post
-- ----------------------------
DROP TABLE IF EXISTS `campus_post`;
CREATE TABLE `campus_post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'postid',
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `subject_id` bigint(20) NOT NULL COMMENT '主题id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `photo_url` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '图片地址',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'post状态，0草稿，1未审核，2已发布',
  `liked` bigint(20) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `comment` bigint(20) NOT NULL DEFAULT 0 COMMENT '评论数',
  `collect` bigint(20) NOT NULL DEFAULT 0 COMMENT '收藏数',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '更新版本号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fkey_subject`(`subject_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'post表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of campus_post
-- ----------------------------
INSERT INTO `campus_post` VALUES (1, '甜筒的一百种吃法', '231887539168870401', 4, '甜筒蛋糕。这种吃法主要是把甜筒的形状和口感融入到蛋糕的制作中，使得蛋糕既有甜筒的口感，又有其他蛋糕的口感。\r\n脆皮甜筒。这是把甜筒的皮炸至金黄酥脆，里面填充上各种馅料，比如巧克力、冰淇淋等。\r\n甜筒沙拉。这种吃法主要是把甜筒和其他食材（比如蔬菜、水果等）混在一起做成沙拉，以增加口感的多样性。\r\n巧克力甜筒。这是一种以巧克力为主要馅料的甜筒，外面是甜筒的形状，里面是满满的巧克力。\r\n甜筒小熊馒头。这种吃法主要是把甜筒做成小熊的形状，同时还可以在表面加上各种装饰，比如巧克力、糖果等。\r\n马铃薯水果甜筒。这种吃法主要是把马铃薯和水果混合在一起，然后装进甜筒里，使得甜筒既有马铃薯的口感，又有水果的口感。', 'http://47.113.185.139:9000/click/file/9fd9724eb8044ee0a6d65a7bff2acbf1.jpg', 2, 5, 0, 0, '2023-09-19 20:08:24', '2023-09-19 20:08:28', 0, 0);
INSERT INTO `campus_post` VALUES (2, 'dfad', '232627360170508290', 4, '444', '2132', 2, 0, 0, 0, '2023-09-19 21:20:02', '2023-09-19 21:20:06', 0, 0);
INSERT INTO `campus_post` VALUES (3, '一锅炖', '232627360170508290', 4, '炖是中国最古老的做菜方式。镇坪有一道特色美食“乡村土灶一锅炖”，已经流传几千年了，一个土灶一口铁锅，一堆熊熊燃烧的火焰，看着就让人眼馋，吃起来更加过瘾。相传唐宋以来，川陕鄂古盐道就盛极一时。而一锅炖的发明者就是曾经在这条古道上背盐的盐夫们，逐渐形成背盐山民的奇特民俗。  为了路途的便利，采用一锅“炖”这种单一快捷的烹饪手段来处理不同的食物原料，即简单又方便。在具有食物多样性的同时，还可以选择重或清淡口味，常在一起搭伴的人都围着这一锅，每个人均可平等参与和分享，边吃边唱五句子山歌聊家常。盐道崎岖坎坷，漫长艰难，是一条非常危险的路。为了生存，需要大家伙相互鼓鼓劲、加加油。吃一锅炖就意味着彼此的认同、亲近，互帮互助，非常具有包容性。炖在锅里的不仅仅是美食，还有温暖和希望。  来到镇坪，一定要去吃一锅炖，否则是一件非常遗憾的事情。镇坪乡村土灶一锅炖的老板叫谭勇，高大帅气，耿直率直，他说做一锅炖的初衷就是为了怀念爷爷和寄托乡愁，爷爷辈都是背盐的，爷爷喜欢吃一锅炖，逢年过节的时候一家人吃着一锅炖，听爷爷讲古盐道那些久远的故事，舒服而惬意。一锅炖伴随着他的成长，有一年他出门在外，想念亲人和家里的一锅炖，跑遍全城都找不到思念中的味道。一个大胆的想法从谭勇的心底萌生：“何不把镇坪古盐道一锅炖这道美食推广开来，让人们都能够品尝到！”他开始了对饮食文化的研究，了解得越多，对一锅炖的兴趣就越浓，越觉得有意义、有价值。也越来越清晰地认识到中国饮食文化是一种广视野、深层次、多角度、高品位的悠久区域文化，是中华各族人民在上下五千年的生产和生活实践中，在食源开发、食具研制、食品调理、食物搭配、营养保健和饮食审美等方面创造、积累并影响周边国家和世界的物质财富及精神财富。  瞬间的想法，改变了谭勇的人生轨迹。说干就干，2013年，他在县城租赁了店面，在乡下设立了食品蔬菜种植基地。热闹的开业了，如他调研和预料的一样，一锅炖很符合秦巴山一带人们的饮食习惯，男女老少皆宜。生意红红火火，经常是晚一点就订不到桌子。2018年他索性买了县城中心位置的一层门面房，扩建开业后依然是座无虚席，生意爆满。  他还热心参与各种饮食文化活动，镇坪腊肉节上，在腊味小镇的一整条街上摆满了一锅炖，浓香四溢，吸引了所有人，让各地前来参观的客商大饱口福，赞不绝口。  那一口口大铁锅里的五花肉，腊排骨、乌鸡、干肚片、秘制蹄筋、豆腐皮、豆油皮、农家蔬菜……，每一株白菜、每一根葱蒜，都是绿油油，水嫩嫩的，让人垂涎欲滴。而且都是镇坪纯天然绿色食品，本地特色。荤菜，素菜都有，可以选择清淡可口型的，也可以选择麻辣鲜香型的，各种口味应有尽有。吃起来热呼呼、香喷喷的，配不配调味料都是一番美味。  谭勇介绍说，一锅炖里面的食材是最讲究的，要想汤香味美，口味独特，镇坪乌鸡、洋芋都是国家地标产品，镇坪腊肉，蔬菜选用绿色有机的农家菜。鸡是放养养殖，在保证肉源、菜园安全情况下也保证了菜品质量。具有超高的营养价值，且安全可靠。乌鸡，腊肉加上各类配菜，经大厨的烹饪，再放入锅中炖，使得土鸡充分吸收其中的精华，汤更是整锅菜的味觉核心，口味集聚升华、回味无穷。  一锅炖还开发配套的有各种各样的小吃和酒水，小零食是地道的镇坪风味:炸酥肉，芝麻汤圆、花椒叶、天星米粑粑、苦荞饼、浆巴馍，还有黄金洋芋……，酒水是自酿的苞谷酒、高粱酒、五味子酒、猕猴桃酒等，每一款都是美味，让人齿颊留香，回味无穷。  镇坪是长寿文化之乡，长寿老人多。药膳一锅炖是根据长寿老人的食谱总结而成的，大山里中药材众多，金钗石斛、野山参、称筋草等等都可入锅。吃了强筋壮骨，美容养颜，延年益寿。  一锅炖不仅是美食，还蕴含着丰厚饮食文化的内涵，为人们品尝倍添雅趣。吃一锅炖时，男女老少、亲朋好友围着热气腾腾的一锅，把臂共话，举箸大啖，温情荡漾，洋溢着热烈融洽的气氛，符合了红红火火团团圆圆这一中国传统文化。  一锅炖店铺和食材基地里解决了50多名农民工的就业问题，让大家在家门口就能打工赚钱。谭勇说自己也是农民的儿子，很想带领父老乡亲们共同富裕。看着大家对一锅炖的喜爱，决定扩大经营规模，让更多的人能够品尝到一锅炖的美味，在安康、岚皋、汉阴、竹溪、巫溪等周边地区都逐步开起了连锁店。  一锅炖来源于古盐道，升华于民间。承载了镇坪人太多的记忆，那独特的味道伴随着一代又一代人的成长，积淀着浓郁的乡愁。现在又成为农民增收脱贫致富的好产业。作为一种特色饮食，亦是一种文化的符号，记得住乡愁，挂得住乡情，一锅炖已成为大巴山一带的代表美食。', 'post/0ee1391bf8d844e5bfb979c478260464.jpg,post/13600c8d773843c3ad3917af0bc4b52e.jpg,post/540b1bf635a14d12b30d749108af4906.jpg,post/23ab22b560754ac3a2474bab7a6fa59e.png,post/6e967400645d495fbb6c34b9a976e4a1.jpg,post/79484a1f2117465f8b9a3deaa92a9438.jpg,post/373d0f8b04104bd8a609436b7826d11c.jpg,post/9b490c7f7a1d46929561bb6c2acc5ce5.jpg,post/d60c18dc7f834d95beea631b01cc41b2.jpg', 2, 0, 0, 0, '2023-09-20 12:59:11', '2023-09-20 12:59:11', 0, 0);
INSERT INTO `campus_post` VALUES (4, '5555555', '232627360170508290', 1, '范德萨发山东阿斯顿发手动阀', 'post/3331c1165c7e4fe8b173fb62b10fdcd0.png,post/d850f765480846dba2c100add04bcef5.png', 2, 0, 0, 0, '2023-09-20 13:52:18', '2023-09-20 13:52:18', 0, 0);
INSERT INTO `campus_post` VALUES (5, '啊对对对', '232627360170508290', 1, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/c2f4e24e27fd437bb22c9e0f98f13a8d.jpg,post/f56e6c69d6f94ceabdaaac14576611c6.jpg,post/6beb55644d1343c18f580a21cd2f3875.jpg', 2, 0, 0, 0, '2023-09-23 17:45:21', '2023-09-23 17:45:21', 0, 0);
INSERT INTO `campus_post` VALUES (6, '啊对对对', '232627360170508290', 2, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/f992fb7cd0b94ec2948f2cb7ae644bdb.png,post/190c48f8da6d413fbdf862ac0f3452bc.png,post/af6f2b1b37fc42b489132e7e0bf83c53.png', 2, 0, 0, 0, '2023-09-23 17:45:46', '2023-09-23 17:45:46', 0, 0);
INSERT INTO `campus_post` VALUES (7, '啊对对对', '232627360170508290', 2, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/6a2210a1910f4b368e86f762263e6045.jpg', 2, 0, 0, 0, '2023-09-23 17:46:04', '2023-09-23 17:46:04', 0, 0);
INSERT INTO `campus_post` VALUES (8, '啊对对对', '232627360170508290', 2, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/62c5b3c3c25047c2bdfe955f1d20dcf8.png,post/267d62d98be341d1900030a8ea1f5362.png,post/5ef5fc280349416e8890889eb7fd148f.png,post/ef9f810def7748fda58218676bebcbed.png', 3, 0, 0, 0, '2023-09-23 17:47:40', '2023-09-23 17:47:40', 0, 0);
INSERT INTO `campus_post` VALUES (9, '啊对对对', '232627360170508290', 3, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/1f3be308cce549e5aac82fec3054f77e.png,post/280090295985417abecad1bf7df6943d.png,post/b5ea2b8a1fa643c4924305b00668c04d.png,post/bdbb449688b94e5ea0118f40902364f4.png', 2, 0, 0, 0, '2023-09-23 17:48:15', '2023-09-23 17:48:15', 0, 0);
INSERT INTO `campus_post` VALUES (10, '啊对对对', '232627360170508290', 4, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/1a37bd908f214d928d7754116d165312.png,post/230329ce76fe495788cd472afa01a053.png,post/281046fe214a43b0a876f29725e0588f.png,post/858880a590a44c829f1b8355dad6afe3.png', 2, 0, 0, 0, '2023-09-23 17:48:21', '2023-09-23 17:48:21', 0, 0);
INSERT INTO `campus_post` VALUES (11, '啊对对对', '232627360170508290', 5, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/2ba181accb2f48a7b78a5069343d6489.png,post/5403e22282684f58b8e930046226a61d.png,post/87287b63bcf9470580e64d18c58b6969.png,post/37a56dbd9eb8486c95ad118de26ec260.png', 2, 0, 0, 0, '2023-09-23 17:48:24', '2023-09-23 17:48:24', 0, 0);
INSERT INTO `campus_post` VALUES (12, '啊对对对', '232627360170508290', 6, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/b5f2a1867a1e4c488808fe24a28d7ab1.png,post/771db3690c6e402fb08aec6d7e4120b2.png,post/5efb88f49e02438486064abee3fbc677.png,post/631c050c577b43ca85c17351b309ce1e.png', 2, 0, 0, 0, '2023-09-23 17:48:30', '2023-09-23 17:48:30', 0, 0);
INSERT INTO `campus_post` VALUES (15, '啊对对对', '232627360170508290', 4, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/4846600874194498a096f6ab20b684c7.png,post/3fa0524e8ea14a28b6cbe63c60373358.png,post/64efafd26e874e00a26752d4406d2ec7.png,post/7ad29479f0424bcca9ac95e173a0537b.png', 2, 0, 0, 0, '2023-09-23 17:55:24', '2023-09-23 17:55:24', 0, 0);
INSERT INTO `campus_post` VALUES (16, '啊对对对', '232627360170508290', 4, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/0185bf1117ea49eea53c726c824d3100.png,post/025b15d74c414f44bd8e41d9df1c0de1.png,post/921b056791f741dcbf760b1d0d34eb18.png,post/55413099d49d4a5ab5d9172280beac0a.png', 2, 0, 0, 0, '2023-09-23 18:02:27', '2023-09-23 18:02:27', 0, 0);
INSERT INTO `campus_post` VALUES (17, '啊对对对', '232627360170508290', 4, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/9ad428414e314b04b3dfa15e8bc91ae7.png,post/1e974423c1dd4a0d9d45560257ac8e75.png,post/b963d9a1c5db4677bdbb9c732a5b3620.png,post/219a76a9635b46ce922415475b7225a1.png', 2, 0, 0, 0, '2023-09-23 18:04:09', '2023-09-23 18:04:09', 0, 0);
INSERT INTO `campus_post` VALUES (18, '啊对对对', '232627360170508290', 3, '  计算机领域的知识覆盖面很广并且更新速度很快，因此保持终身学习的习惯很重要。但在日常开发和学习的过程中，我们获取知识的来源相对复杂且细碎。有成百上千页 的文档手册，也有寥寥数语的博客，甚至闲暇时手机上划过的某则新闻和公众号都有可能包含我们感兴趣的知识。因此，如何利用现有的各类工具，形成一套适合自己的学习工作流，将不同来源的知识碎片整合进属于自己的知识库，方便之后的查阅与复习，就显得尤为重要。经过两年工作之余的学习后，我磨合出了以下学习工作流：', 'post/a86c545ddb3e4b409ff9cc56e7c18601.png,post/346c0cfaa6ab42e5a08a0e4e7476d344.png,post/7d0c893683bd42aa9ff3236c7dca2d92.png,post/251053c2d16247b7a513587ad722d740.png', 2, 0, 0, 0, '2023-09-23 18:15:08', '2023-09-23 18:15:08', 0, 0);

-- ----------------------------
-- Table structure for campus_postliked
-- ----------------------------
DROP TABLE IF EXISTS `campus_postliked`;
CREATE TABLE `campus_postliked`  (
  `post_id` bigint(20) NOT NULL COMMENT 'postId',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`post_id`, `user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of campus_postliked
-- ----------------------------
INSERT INTO `campus_postliked` VALUES (1, '232627360170508290', '2023-09-22 13:30:07');

-- ----------------------------
-- Table structure for campus_subject
-- ----------------------------
DROP TABLE IF EXISTS `campus_subject`;
CREATE TABLE `campus_subject`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主题id',
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主题名称',
  `subject_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主题图片',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '更新版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '主题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of campus_subject
-- ----------------------------
INSERT INTO `campus_subject` VALUES (1, '寻物启示', '', '2023-09-19 16:36:11', '2023-09-19 16:36:18', 0, 0);
INSERT INTO `campus_subject` VALUES (2, '失物招领', '', '2023-09-19 16:37:04', '2023-09-19 16:37:07', 0, 0);
INSERT INTO `campus_subject` VALUES (3, '二手交易', '', '2023-09-19 16:37:23', '2023-09-19 16:37:28', 0, 0);
INSERT INTO `campus_subject` VALUES (4, '美食推荐', '', '2023-09-19 16:37:51', '2023-09-19 16:37:54', 0, 0);
INSERT INTO `campus_subject` VALUES (5, '找搭子', '', '2023-09-19 16:38:23', '2023-09-19 16:38:25', 0, 0);
INSERT INTO `campus_subject` VALUES (6, '比赛相关', '', '2023-09-19 16:38:46', '2023-09-19 16:38:49', 0, 0);

-- ----------------------------
-- Table structure for campus_user
-- ----------------------------
DROP TABLE IF EXISTS `campus_user`;
CREATE TABLE `campus_user`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户表ID',
  `inviter` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邀请者',
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户密码',
  `true_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT '性别：0 未知， 1男， 2女',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '点击设置我的签名' COMMENT '个性标签',
  `back_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '背景图片',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `share_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人分享图片',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `last_login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `user_level` tinyint(4) NULL DEFAULT 0 COMMENT '0 普通用户，1 VIP用户，2 高级VIP用户',
  `integral` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '用户等级',
  `nick_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称或网络名称',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户手机号码',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像图片',
  `openid` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信登录openid',
  `session_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信登录会话KEY',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0 可用, 1 禁用, 2 注销',
  `add_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '租户ID，用于分割多个租户',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '更新版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of campus_user
-- ----------------------------
INSERT INTO `campus_user` VALUES ('231887539168870401', NULL, NULL, '', NULL, 0, '此用户并未填写标签', NULL, NULL, NULL, '2023-09-17 21:22:06', '', 0, 0.00, 'user_xxmtingfi1', '', '', '45235', '', 0, '2023-09-17 21:22:06', '2023-09-17 21:22:06', 1, '0', 0);
INSERT INTO `campus_user` VALUES ('232544536021172240', NULL, NULL, '', NULL, 0, '此用户并未填写标签', NULL, NULL, NULL, '2023-09-19 15:51:35', '', 0, 0.00, 'user_blal37fsoz', '', '', '23234', '', 0, NULL, NULL, 1, '0', 0);
INSERT INTO `campus_user` VALUES ('232544986992738321', NULL, NULL, '', NULL, 0, '此用户并未填写标签', NULL, NULL, NULL, '2023-09-19 15:53:20', '', 0, 0.00, 'user_llyrm4fvse', '', '', '{\"code\":\"234\"}', '', 0, NULL, NULL, 1, '0', 0);
INSERT INTO `campus_user` VALUES ('232545549633454098', NULL, NULL, '', NULL, 0, '此用户并未填写标签', NULL, NULL, NULL, '2023-09-19 15:55:31', '', 0, 0.00, 'user_vtipzpexdi', '', '', '{\r\n    \"code\": \"234\"\r\n}', '', 0, NULL, NULL, 1, '0', 0);
INSERT INTO `campus_user` VALUES ('232546318432600083', NULL, NULL, '', NULL, 0, '此用户并未填写标签', NULL, NULL, NULL, '2023-09-19 15:58:30', '', 0, 0.00, 'user_fujgdxflwg', '', '', '234', '', 0, NULL, NULL, 1, '0', 0);
INSERT INTO `campus_user` VALUES ('232551489573224450', NULL, NULL, '', NULL, 0, '此用户并未填写标签', NULL, NULL, NULL, '2023-09-19 16:18:34', '', 0, 0.00, 'user_obqxutwncn', '', '', '234', '', 0, '2023-09-19 16:18:34', '2023-09-19 16:18:34', 1, '0', 0);
INSERT INTO `campus_user` VALUES ('232627360170508290', NULL, NULL, '', NULL, 0, '此用户并未填写标签', NULL, NULL, NULL, '2023-09-19 21:12:58', '', 0, 0.00, 'user_t3pc8uck5e', '', '', '234', '', 0, '2023-09-19 21:12:58', '2023-09-19 21:12:58', 0, '0', 0);

SET FOREIGN_KEY_CHECKS = 1;
