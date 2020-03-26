package com.sunlands.intl.yingshi.ui.activity.home.scorequery;

import java.util.List;

/**
 * 文件名: ScoreResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/10
 */
public class ScoreResponse {


    /**
     * hasMore : 1
     * scoreList : [{"subjectId":2,"subjectName":"MBA290(18OCT)","examList":[{"title":"1229","type":"随堂考","score":"0"}]},{"subjectId":5,"subjectName":"内测","examList":[{"title":"ss","type":"随堂考","score":"0"},{"title":"测试考试2","type":"随堂考","score":"0"},{"title":"测试考试","type":"测验","score":"0"},{"title":"测试考试123","type":"测验","score":"0"},{"title":"测试","type":"测验","score":"0"},{"title":"考试提醒测试","type":"测验","score":"0"},{"title":"随堂考测试001","type":"随堂考","score":"0"},{"title":"随堂考测试","type":"随堂考","score":"0"},{"title":"测试随堂考","type":"随堂考","score":"0"},{"title":"随堂考1228","type":"随堂考","score":"0"},{"title":"                                                  ","type":"随堂考","score":"0"},{"title":"测试考试0103","type":"随堂考","score":"0"},{"title":"测试考试002","type":"随堂考","score":"0"},{"title":"随堂考测试01","type":"随堂考","score":"0"},{"title":"考试推送","type":"测验","score":"0"},{"title":"开始推送1","type":"测验","score":"0"},{"title":"6666测试","type":"随堂考","score":"0"},{"title":"期中考试测试","type":"期中考试","score":"0"},{"title":"1234","type":"随堂考","score":"0"},{"title":"练习","type":"测验","score":"0"},{"title":"联系考试","type":"测验","score":"0"},{"title":"推送","type":"测验","score":"0"},{"title":"期中考试","type":"期中考试","score":"0"},{"title":"随堂考推送","type":"随堂考","score":"0"},{"title":"期末考试","type":"期末考试","score":"0"},{"title":"期末考试666","type":"期末考试","score":"0"},{"title":"随堂考666","type":"随堂考","score":"0"},{"title":"测试随堂考111","type":"随堂考","score":"0"},{"title":"期中考试111","type":"期中考试","score":"0"},{"title":"随堂考1.15","type":"随堂考","score":"0"},{"title":"测试推送","type":"随堂考","score":"0"},{"title":"考试push推送","type":"测验","score":"0"},{"title":"其中考试11","type":"期中考试","score":"0"},{"title":"suitangkaoshi","type":"随堂考","score":"0"},{"title":"音频题重复","type":"随堂考","score":"0"},{"title":"综合体音频题测试","type":"期末考试","score":"0"},{"title":"ssss","type":"随堂考","score":"-"},{"title":"huang","type":"期末考试","score":"0"},{"title":"On Influence of Cultural Differences on Associative Meanings and Translation","type":"论文","score":"0"}]},{"subjectId":6,"subjectName":"内部测试","examList":[{"title":"sep口语测试","type":"测验","score":"0"}]},{"subjectId":8,"subjectName":"英语课－写作","examList":[{"title":"level1写作第一次课作业","type":"测验","score":"0"},{"title":"level1写作第一次课作业（for退出重进的同学）","type":"测验","score":"0"},{"title":"level1写作第二次课作业","type":"测验","score":"0"},{"title":"level1写作第二次课作业（超时没做和新加入的同学）","type":"测验","score":"0"},{"title":"level1写作第三次课作业","type":"测验","score":"0"},{"title":"level第一次写作课程作业（for后期加入的学生）","type":"测验","score":"0"},{"title":"level1第二次写作作业（for后期加入的小伙伴）","type":"测验","score":"0"},{"title":"level1 第三次写作作业（for后期加入的小伙伴）","type":"测验","score":"0"},{"title":"SEP写作level2第一次课","type":"测验","score":"0"},{"title":"SEP写作level2第二次课","type":"测验","score":"0"},{"title":"SEP写作level2第三次课","type":"测验","score":"0"},{"title":"SEP写作level1第一次课","type":"测验","score":"0"}]},{"subjectId":9,"subjectName":"英语课－听力","examList":[{"title":"国际MBA 听力Level 1 课后作业1","type":"测验","score":"0"},{"title":"国际MBA 听力Level 1 课后作业1","type":"测验","score":"0"},{"title":"国际MBA听力 Level 1 Lesson 2 课后作业","type":"测验","score":"0"}]}]
     */

    private int hasMore;
    private List<ScoreListBean> scoreList;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<ScoreListBean> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<ScoreListBean> scoreList) {
        this.scoreList = scoreList;
    }

    public static class ScoreListBean {
        /**
         * subjectId : 2
         * subjectName : MBA290(18OCT)
         * examList : [{"title":"1229","type":"随堂考","score":"0"}]
         */

        private int subjectId;
        private String subjectName;
        private List<ExamListBean> examList;

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public List<ExamListBean> getExamList() {
            return examList;
        }

        public void setExamList(List<ExamListBean> examList) {
            this.examList = examList;
        }

        public static class ExamListBean {
            /**
             * title : 1229
             * type : 随堂考
             * score : 0
             */

            private String title;
            private String type;
            private String score;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }
        }
    }
}
