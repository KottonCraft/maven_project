delete from users;
delete from cards;
-- 创建测试用户（密码均为123456）
INSERT INTO users (username, password, email, phone) VALUES
('lxm', '$2a$10$8vZU7g5Z/z3Z9d0Ebb7QvOj5T7lB1F6v3W5oNq1pLkJs6VYJ2r1O', 'lxm@edu.cn', '13812345678'),
('wangfang', '$2a$10$6tS7fW8uX1mRqHwYzV4P0eB3nC4d5E6f7G8h9i0j1k2l3m4n5o6p', 'wangfang@example.com', '13987654321'),
('luochen', '$2a$10$9wX8yV7U6T5R4E3W2Q1Z0zA9B8C7D6E5F4G3H2I1J0K1L2M3N4O5P', 'luochen@tech.com', '18911223344');

-- 获取用户ID
SET @li_id = (SELECT id FROM users WHERE username = 'lxm');
SET @wang_id = (SELECT id FROM users WHERE username = 'wangfang');
SET @lc_id = (SELECT id FROM users WHERE username = 'luochen');

-- 李晓明的名片（激活最后一张）
INSERT INTO cards (user_id, card_name, card_title, position, company, contact_info, is_active) VALUES
(@li_id, '李小明', '技术方向简历', 'Java开发实习生', '腾讯科技', '微信：wx_lxm', 0),
(@li_id, '李小明', '学术方向简历', '科研助理', '浙江大学人工智能实验室', '微信：wx_lxm', 0),
(@li_id, '李小明', '实习简历', '后端开发实习生', '阿里巴巴集团', '领英：linkedin.com', 0),
(@li_id, '小明同学', '比赛作品集', '创新大赛选手', '全国大学生创新创业大赛', '微信：lxm_contest', 0),
(@li_id, '李小明', '正式求职版', '应届毕业生-软件工程', '浙江大学', '个人博客：lxm-blog.tech', 1);

-- 王芳的名片（激活第二张）
INSERT INTO cards (user_id, card_name, card_title, position, company, contact_info, is_active) VALUES
(@wang_id, '王芳', '设计方向简历', 'UI设计实习生', '字节跳动', '微信：wf_design', 0),
(@wang_id, '王芳', '正式求职版', '视觉设计师应届生', '中国美术学院', '微信：wx_wf', 1),
(@wang_id, '芳芳', '插画作品集', '自由插画师', '个人工作室', '站酷：wangfang.zcool.com', 0),
(@wang_id, '王芳', '实习简历', '平面设计实习生', '美团设计中心', '作品集：wf-portfolio.com', 0),
(@wang_id, '王芳', '学术简历', '数字媒体艺术研究生', '清华大学美术学院', '微信：wx_lxm', 0);

-- 张伟的名片（激活第三张）
INSERT INTO cards (user_id, card_name, card_title, position, company, contact_info, is_active) VALUES
(@lc_id, '罗辰',  '技术管理方向', '产品经理实习生', '华为技术有限公司', '微信：lc_pm', 0),
(@lc_id, '辰哥',  '创业项目', '校园创业团队负责人', '智能硬件创业项目', '微信：lc_startup', 0),
(@lc_id, '罗辰',  '正式求职版', '电子信息工程应届生', '电子科技大学', 'GitHub：lc_electronics', 1),
(@lc_id, '辰哥',  '竞赛简历', '全国电子设计大赛选手', '全国大学生电子设计竞赛', '邮箱：lc@contest.com', 0),
(@lc_id, '罗辰',  '学术研究', '科研助理', '中科院自动化所', '学术主页：lc-research.ac.cn', 0);