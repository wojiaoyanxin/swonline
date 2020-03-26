package com.sunlands.intl.yingshi.bean;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.Utils;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.IHeaderHelper;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.view.FriendListActivity;
import com.sunlands.intl.yingshi.ui.community.view.MasterActivity;
import com.sunlands.intl.yingshi.ui.community.view.MyCollectActivity;
import com.sunlands.intl.yingshi.util.GlideUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/2/25 15:30
 * 备注：
 */
public class MainlBean implements IHeaderHelper {


    public int start;
    public String limit;
    public String total;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getLimit() {
        return limit == null ? "" : limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getTotal() {
        return total == null ? "" : total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<String> getHead_img_url() {
        if (head_img_url == null) {
            return new ArrayList<>();
        }
        return head_img_url;
    }

    public void setHead_img_url(List<String> head_img_url) {
        this.head_img_url = head_img_url;
    }

    public List<ChannelListBean> getChannel_list() {
        if (channel_list == null) {
            return new ArrayList<>();
        }
        return channel_list;
    }

    public void setChannel_list(List<ChannelListBean> channel_list) {
        this.channel_list = channel_list;
    }

    public List<ListBean> list;
    public List<String> head_img_url;
    public List<ChannelListBean> channel_list;


    IViewSelect mIViewSelect;

    public MainlBean setViewSelect(IViewSelect viewSelect) {
        this.mIViewSelect = viewSelect;
        return this;
    }

    public interface IViewSelect {
        void onViewSelect(String type);
    }

    private String mCuttentType = IMessageContract.ConmmunityType.recommend;

    public void onViewSelect(TextView selectView, View selectLine, TextView noselectView, View noselectLine, View hotIcon) {
        if (selectView.getId() == R.id.tv_comm_hotlist) {
            mCuttentType = IMessageContract.ConmmunityType.view;
            hotIcon.setVisibility(View.VISIBLE);
        }
        if (selectView.getId() == R.id.tv_comm_recommend) {
            mCuttentType = IMessageContract.ConmmunityType.recommend;
            hotIcon.setVisibility(View.GONE);
        }
        selectView.setTextSize(18);
        selectView.setTextColor(CommonUtils.getColor(R.color.cl_333333));
        selectView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        selectLine.setVisibility(View.VISIBLE);

        noselectView.setTextSize(14);
        noselectView.setTextColor(CommonUtils.getColor(R.color.cl_999999));
        noselectLine.setVisibility(View.GONE);
        noselectView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        if (mIViewSelect != null) {
            mIViewSelect.onViewSelect(mCuttentType);
        }
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.head_comm_layout;
    }

    @Override
    public void onBind(ViewHolder holder) {
        TextView tv_comm_hotlist = holder.getView(R.id.tv_comm_hotlist);
        TextView tv_comm_recommend = holder.getView(R.id.tv_comm_recommend);
        View v_comm_hotlist = holder.getView(R.id.v_comm_hotlist);
        View v_comm_recommend = holder.getView(R.id.v_comm_recommend);
        RecyclerView rv_head_list = holder.getView(R.id.rv_head_list);
        if (getHead_img_url().size() == 0) {
            holder.getView(R.id.cl_friend).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.cl_friend).setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ApplicationsHelper.context(), LinearLayoutManager.HORIZONTAL, false);
            linearLayoutManager.setStackFromEnd(false);
            rv_head_list.setLayoutManager(linearLayoutManager);
            rv_head_list.setAdapter(new CommonAdapter<String>(ApplicationsHelper.context(), getHead_img_url(), R.layout.comm_head_list_layout) {
                @Override
                public void convert(ViewHolder headholder, String s) {
                    if (headholder.getLayoutPosition() == 0) {
                        headholder.getView(R.id.v_comm_head_v).setVisibility(View.VISIBLE);
                    } else {
                        headholder.getView(R.id.v_comm_head_v).setVisibility(View.GONE);
                    }
                    GlideUtils.loadImage(Utils.getApp(), s, headholder.getView(R.id.head_img_item));
                    headholder.itemView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return holder.getView(R.id.cl_friend).performClick();
                        }
                    });
                }
            });
        }

        RxBindingHelper.setOnClickListener(holder.getView(R.id.tv_comm_kyzq), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                //考研专区
                Intent intent = new Intent(ActivityUtils.getTopActivity(), MasterActivity.class);
                intent.putExtra(MasterActivity.CHANNEL_INFO, getChannel_list().get(1));
                ActivityUtils.startActivity(intent);
            }
        });
        RxBindingHelper.setOnClickListener(holder.getView(R.id.tv_comm_gjss), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                //国际硕士
                Intent intent = new Intent(ActivityUtils.getTopActivity(), MasterActivity.class);
                intent.putExtra(MasterActivity.CHANNEL_INFO, getChannel_list().get(2));
                ActivityUtils.startActivity(intent);
            }
        });
        RxBindingHelper.setOnClickListener(holder.getView(R.id.cl_friend), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转同学信息列表
                ActivityUtils.startActivity(FriendListActivity.class);
            }
        });
        RxBindingHelper.setOnClickListener(holder.getView(R.id.tv_comm_wdtz), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的贴子
                ActivityUtils.startActivity(MyCollectActivity.class);
            }
        });

        RxBindingHelper.setOnClickListener(tv_comm_hotlist, new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewSelect(tv_comm_hotlist, v_comm_hotlist, tv_comm_recommend, v_comm_recommend, holder.getView(R.id.iv_hot_icon));
            }
        });
        RxBindingHelper.setOnClickListener(tv_comm_recommend, new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewSelect(tv_comm_recommend, v_comm_recommend, tv_comm_hotlist, v_comm_hotlist, holder.getView(R.id.iv_hot_icon));
            }
        });

    }

    public static class ListBean {
        /**
         * thread : {"id":3286,"user_id":391,"title":"浅谈MBA的社会适用性问题","content":"对于MBA的社会适应性问题，一直存在很大的争议，有些雇主视其如珍宝，愿意在公司内部提供培养和任用机制，使得一些MBA学员能够大施拳脚，也取得了不俗的成绩。但是也有一些极端现象，一些雇主视之如\u201c敝履\u201d，造成这种情况的原因可能是雇主曾任用过此类人员，但是并不能达到自己的预期效果，从而在以后的观念中造成了一种\u201c刻板成见\u201d。不论是哪种情况，都是有一定的社会诱因，我们来简单谈一谈。1、双方的期望都太高部分企业之所以对MBA教育泼冷水,大部分原因来自于认为招聘的MBA学生普遍存在眼高手低的问题,不具备脚踏实地的精神。换句话说也就是认为MBA学员对毕业后的工作期望值太高,往往与企业对他的要求相矛盾,或者满足了高的期望后,而他的实际工作能力又无法与之匹配。但是同样，企业同样存在高期望的问题，很多企业期待招到一个MBA学生以后,什么都能干,干什么都干得很好。如果没有达到这个目标,企业就会由很高的期望转到失望。然而如果商学院培养的MBA学生,到了你企业以后,一开始用就能发挥他最大的效用,你的企业肯定要完蛋。因为任何可以从市场上买来的东西,都不是核心竞争力。从商学院或者其他什么地方培养出来的优秀人才,必须要在你的企业里面得到很好的整合,通过文化、制度和理念的锤炼,使他能够最大的发挥出作用来，这样才能够建立起企业独特的竞争能力。读MBA的学生和用MBA的企业，都要清楚地认识这一点，培养人是一个过程，我们不能期望体育学院培养出来的人，马上能当中国国家队的主教练，而他肯定也当不了。现在企业也是一样，做得好的企业，好的企业家，一定是通过较长时间才能培养出来的。2、双方互相选择时的认知误区国内企业存在一种人才高消费的现象。从企业来讲他认为我要招最好的商学院的学生,或者是最优秀的员工。你招大专,我就招研究生,这个找MBA,那个就找博士,很多时候找来的人除了做花瓶外什么也做不了。企业用人应该根据岗位不同寻找找最合适的人才。同样，MBA或EMBA毕业生也要根据自身条件找到最合适的位置,进入任何企业都要以一种谦卑的学习态度，多数情况下，在未实际做出成绩之前，MBA学位只是个敲门砖，并不是你可以在企业立刻飞升的砝码。MBA不是一个商品,不同学校的MBA学员有不同的性格和特征。就如同跟他们进校的时候不一样。离校的时候也不一样。所以实际上真正需要关心的是,企业对于MBA错误的使用,或者说这个学员一开始就错误地使用了自己学到的知识。很多企业在雇佣MBA/EMBA的时候,并没有了解他们的文化只是适合某种特定的人。所以作为企业来讲,他们需要更多了解怎么样找到合适的人并留住他们。3、培养任用机制VS终身学习观念\u201c千里马常有而伯乐不常有\u201d。企业如果想要将MBA人才培养成企业的核心人才，也要建立起相应的任用和培养机制。知人善用也是一个企业能够发展壮大的无比重要的方面。对于MBA来说，从长期角度来讲，需要建立一个终身学习的理念。不管你曾经得到了什么，不管你过去多成功，随着经济的高速发展，在很短时间内你学到的东西就过时了，所以你要有终身学习的精神。MBA学习的知识是固定的，但是通过学习到的知识结合企业实际进行发挥的空间是无穷无尽的，即使离开了MBA课堂，学员也不能停止学习，否则也会因为不适应社会发展，而被企业所\u201c嫌弃\u201d，被社会所淘汰。","to_user_id":0,"to_group_id":"0","crawled_from":"MBA招生网","is_crawled":6814,"real_view_num":0,"virtual_view_num":43645,"published_at":"昨天 16:18:49","is_fixed_time":0,"fixed_time_at":"2019-04-01 16:18:49","weight":0,"view_num":"1w+","shared_num":0,"liked_num":0,"channel_id":0,"is_stuck":0,"status":1,"stuck_at":"2019-04-01 16:18:49","is_public":0,"img_num":1,"is_deleted":0,"updated_at":"2019-04-01 16:18:49","created_at":"2019-04-01 16:18:49"}
         * image : ["http://bj-test-1254383113.file.myqcloud.com/15541067291463.jpeg"]
         * user_info : {"id":464,"nickname":"160****5818","mobile":"16080355818","is_virtual":1,"app_user_id":"0","head_img_url":"https://education-1254383113.file.myqcloud.com/79203529547743.png"}
         * extend : {"comment_num":"0","is_liked":"0","is_collect":"0"}
         */

        public ThreadBean thread;
        public UserInfoBean user_info;

        public ThreadBean getThread() {
            return thread;
        }

        public void setThread(ThreadBean thread) {
            this.thread = thread;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public ExtendBean getExtend() {
            return extend;
        }

        public void setExtend(ExtendBean extend) {
            this.extend = extend;
        }

        public List<String> getImage() {
            if (image == null) {
                return new ArrayList<>();
            }
            return image;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "thread=" + thread +
                    ", user_info=" + user_info +
                    ", extend=" + extend +
                    ", image=" + image +
                    '}';
        }

        public void setImage(List<String> image) {
            this.image = image;
        }

        public ExtendBean extend;
        public List<String> image;

        public static class ThreadBean implements Serializable {
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getTitle() {
                return title == null ? "" : title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getTo_user_id() {
                return to_user_id;
            }

            public void setTo_user_id(int to_user_id) {
                this.to_user_id = to_user_id;
            }

            public String getTo_group_id() {
                return to_group_id == null ? "" : to_group_id;
            }

            public void setTo_group_id(String to_group_id) {
                this.to_group_id = to_group_id;
            }

            public String getCrawled_from() {
                return crawled_from == null ? "" : crawled_from;
            }

            public void setCrawled_from(String crawled_from) {
                this.crawled_from = crawled_from;
            }

            public int getIs_crawled() {
                return is_crawled;
            }

            public void setIs_crawled(int is_crawled) {
                this.is_crawled = is_crawled;
            }

            public int getReal_view_num() {
                return real_view_num;
            }

            public void setReal_view_num(int real_view_num) {
                this.real_view_num = real_view_num;
            }

            public int getVirtual_view_num() {
                return virtual_view_num;
            }

            public void setVirtual_view_num(int virtual_view_num) {
                this.virtual_view_num = virtual_view_num;
            }

            public String getPublished_at() {
                return published_at == null ? "" : published_at;
            }

            public void setPublished_at(String published_at) {
                this.published_at = published_at;
            }

            public int getIs_fixed_time() {
                return is_fixed_time;
            }

            public void setIs_fixed_time(int is_fixed_time) {
                this.is_fixed_time = is_fixed_time;
            }

            public String getFixed_time_at() {
                return fixed_time_at == null ? "" : fixed_time_at;
            }

            public void setFixed_time_at(String fixed_time_at) {
                this.fixed_time_at = fixed_time_at;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public String getView_num() {
                return view_num == null ? "" : view_num;
            }

            public void setView_num(String view_num) {
                this.view_num = view_num;
            }

            public int getShared_num() {
                return shared_num;
            }

            public void setShared_num(int shared_num) {
                this.shared_num = shared_num;
            }

            public int getLiked_num() {
                return liked_num;
            }

            public void setLiked_num(int liked_num) {
                this.liked_num = liked_num;
            }

            public int getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(int channel_id) {
                this.channel_id = channel_id;
            }

            public int getIs_stuck() {
                return is_stuck;
            }

            public void setIs_stuck(int is_stuck) {
                this.is_stuck = is_stuck;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStuck_at() {
                return stuck_at == null ? "" : stuck_at;
            }

            public void setStuck_at(String stuck_at) {
                this.stuck_at = stuck_at;
            }

            public int getIs_public() {
                return is_public;
            }

            public void setIs_public(int is_public) {
                this.is_public = is_public;
            }

            public int getImg_num() {
                return img_num;
            }

            public void setImg_num(int img_num) {
                this.img_num = img_num;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }

            public String getUpdated_at() {
                return updated_at == null ? "" : updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getCreated_at() {
                return created_at == null ? "" : created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            /**
             * id : 3286
             * user_id : 391
             * title : 浅谈MBA的社会适用性问题
             * content : 对于MBA的社会适应性问题，一直存在很大的争议，有些雇主视其如珍宝，愿意在公司内部提供培养和任用机制，使得一些MBA学员能够大施拳脚，也取得了不俗的成绩。但是也有一些极端现象，一些雇主视之如“敝履”，造成这种情况的原因可能是雇主曾任用过此类人员，但是并不能达到自己的预期效果，从而在以后的观念中造成了一种“刻板成见”。不论是哪种情况，都是有一定的社会诱因，我们来简单谈一谈。1、双方的期望都太高部分企业之所以对MBA教育泼冷水,大部分原因来自于认为招聘的MBA学生普遍存在眼高手低的问题,不具备脚踏实地的精神。换句话说也就是认为MBA学员对毕业后的工作期望值太高,往往与企业对他的要求相矛盾,或者满足了高的期望后,而他的实际工作能力又无法与之匹配。但是同样，企业同样存在高期望的问题，很多企业期待招到一个MBA学生以后,什么都能干,干什么都干得很好。如果没有达到这个目标,企业就会由很高的期望转到失望。然而如果商学院培养的MBA学生,到了你企业以后,一开始用就能发挥他最大的效用,你的企业肯定要完蛋。因为任何可以从市场上买来的东西,都不是核心竞争力。从商学院或者其他什么地方培养出来的优秀人才,必须要在你的企业里面得到很好的整合,通过文化、制度和理念的锤炼,使他能够最大的发挥出作用来，这样才能够建立起企业独特的竞争能力。读MBA的学生和用MBA的企业，都要清楚地认识这一点，培养人是一个过程，我们不能期望体育学院培养出来的人，马上能当中国国家队的主教练，而他肯定也当不了。现在企业也是一样，做得好的企业，好的企业家，一定是通过较长时间才能培养出来的。2、双方互相选择时的认知误区国内企业存在一种人才高消费的现象。从企业来讲他认为我要招最好的商学院的学生,或者是最优秀的员工。你招大专,我就招研究生,这个找MBA,那个就找博士,很多时候找来的人除了做花瓶外什么也做不了。企业用人应该根据岗位不同寻找找最合适的人才。同样，MBA或EMBA毕业生也要根据自身条件找到最合适的位置,进入任何企业都要以一种谦卑的学习态度，多数情况下，在未实际做出成绩之前，MBA学位只是个敲门砖，并不是你可以在企业立刻飞升的砝码。MBA不是一个商品,不同学校的MBA学员有不同的性格和特征。就如同跟他们进校的时候不一样。离校的时候也不一样。所以实际上真正需要关心的是,企业对于MBA错误的使用,或者说这个学员一开始就错误地使用了自己学到的知识。很多企业在雇佣MBA/EMBA的时候,并没有了解他们的文化只是适合某种特定的人。所以作为企业来讲,他们需要更多了解怎么样找到合适的人并留住他们。3、培养任用机制VS终身学习观念“千里马常有而伯乐不常有”。企业如果想要将MBA人才培养成企业的核心人才，也要建立起相应的任用和培养机制。知人善用也是一个企业能够发展壮大的无比重要的方面。对于MBA来说，从长期角度来讲，需要建立一个终身学习的理念。不管你曾经得到了什么，不管你过去多成功，随着经济的高速发展，在很短时间内你学到的东西就过时了，所以你要有终身学习的精神。MBA学习的知识是固定的，但是通过学习到的知识结合企业实际进行发挥的空间是无穷无尽的，即使离开了MBA课堂，学员也不能停止学习，否则也会因为不适应社会发展，而被企业所“嫌弃”，被社会所淘汰。
             * to_user_id : 0
             * to_group_id : 0
             * crawled_from : MBA招生网
             * is_crawled : 6814
             * real_view_num : 0
             * virtual_view_num : 43645
             * published_at : 昨天 16:18:49
             * is_fixed_time : 0
             * fixed_time_at : 2019-04-01 16:18:49
             * weight : 0
             * view_num : 1w+
             * shared_num : 0
             * liked_num : 0
             * channel_id : 0
             * is_stuck : 0
             * status : 1
             * stuck_at : 2019-04-01 16:18:49
             * is_public : 0
             * img_num : 1
             * is_deleted : 0
             * updated_at : 2019-04-01 16:18:49
             * created_at : 2019-04-01 16:18:49
             */

            public int id;
            public int user_id;
            public String title;
            public String content;
            public int to_user_id;
            public String to_group_id;
            public String crawled_from;
            public int is_crawled;
            public int real_view_num;
            public int virtual_view_num;
            public String published_at;
            public int is_fixed_time;
            public String fixed_time_at;
            public int weight;
            public String view_num;
            public int shared_num;
            public int liked_num;
            public int channel_id;
            public int is_stuck;
            public int status;
            public String stuck_at;

            @Override
            public String toString() {
                return "ThreadBean{" +
                        "id=" + id +
                        ", user_id=" + user_id +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", to_user_id=" + to_user_id +
                        ", to_group_id='" + to_group_id + '\'' +
                        ", crawled_from='" + crawled_from + '\'' +
                        ", is_crawled=" + is_crawled +
                        ", real_view_num=" + real_view_num +
                        ", virtual_view_num=" + virtual_view_num +
                        ", published_at='" + published_at + '\'' +
                        ", is_fixed_time=" + is_fixed_time +
                        ", fixed_time_at='" + fixed_time_at + '\'' +
                        ", weight=" + weight +
                        ", view_num='" + view_num + '\'' +
                        ", shared_num=" + shared_num +
                        ", liked_num=" + liked_num +
                        ", channel_id=" + channel_id +
                        ", is_stuck=" + is_stuck +
                        ", status=" + status +
                        ", stuck_at='" + stuck_at + '\'' +
                        ", is_public=" + is_public +
                        ", img_num=" + img_num +
                        ", is_deleted=" + is_deleted +
                        ", updated_at='" + updated_at + '\'' +
                        ", created_at='" + created_at + '\'' +
                        '}';
            }

            public int is_public;
            public int img_num;
            public int is_deleted;
            public String updated_at;
            public String created_at;
        }

        public static class UserInfoBean {
            /**
             * id : 464
             * nickname : 160****5818
             * mobile : 16080355818
             * is_virtual : 1
             * app_user_id : 0
             * head_img_url : https://education-1254383113.file.myqcloud.com/79203529547743.png
             */

            public int id;
            public String nickname;
            public String mobile;
            public int is_virtual;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname == null ? "" : nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile == null ? "" : mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getIs_virtual() {
                return is_virtual;
            }

            public void setIs_virtual(int is_virtual) {
                this.is_virtual = is_virtual;
            }

            public String getApp_user_id() {
                return app_user_id == null ? "" : app_user_id;
            }

            public void setApp_user_id(String app_user_id) {
                this.app_user_id = app_user_id;
            }

            public String getHead_img_url() {
                return head_img_url == null ? "" : head_img_url;
            }

            public void setHead_img_url(String head_img_url) {
                this.head_img_url = head_img_url;
            }

            public String app_user_id;
            public String head_img_url;
        }

        public static class ExtendBean {
            public String getComment_num() {
                return comment_num == null ? "0" : comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getIs_liked() {
                return is_liked == null ? "0" : is_liked;
            }

            public void setIs_liked(String is_liked) {
                this.is_liked = is_liked;
            }

            public String getIs_collect() {
                return is_collect == null ? "0" : is_collect;
            }

            public void setIs_collect(String is_collect) {
                this.is_collect = is_collect;
            }

            @Override
            public String toString() {
                return "ExtendBean{" +
                        "comment_num='" + comment_num + '\'' +
                        ", is_liked='" + is_liked + '\'' +
                        ", is_collect='" + is_collect + '\'' +
                        '}';
            }

            /**
             * comment_num : 0
             * is_liked : 0
             * is_collect : 0
             */

            public String comment_num;
            public String is_liked;
            public String is_collect;
        }
    }

    public static class ChannelListBean implements Serializable {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg_id() {
            return img_id == null ? "" : img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getUpdated_at() {
            return updated_at == null ? "" : updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at == null ? "" : created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getImg_url() {
            return img_url == null ? "" : img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        /**
         * id : 0
         * title : 默认
         * img_id : 125424788027599.png
         * status : 1
         * is_deleted : 0
         * updated_at : 2019-03-29 17:54:50
         * created_at : 2019-03-14 17:50:48
         * img_url : http://bj-test-1254383113.file.myqcloud.com/125424788027599.png
         */

        public int id;
        public String title;
        public String img_id;
        public int status;
        public int is_deleted;
        public String updated_at;
        public String created_at;
        public String img_url;
    }
}
