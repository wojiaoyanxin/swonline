package com.sunlands.intl.yingshi.bean;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.IHeaderHelper;
import com.sunlands.comm_core.rvadapter.OnItemClickListener;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.community.other.NineGridLayoutManager;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.PicHelpter;
import com.xueh.picselect.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/4/11 11:30
 * 备注：
 */
public class ThreadInfoBean implements IHeaderHelper {


    /**
     * thread : {"id":4616,"user_id":187,"title":"了","content":"了","to_user_id":0,"to_group_id":"0","crawled_from":"","is_crawled":0,"real_view_num":2,"virtual_view_num":0,"published_at":"昨天 21:24:13","is_fixed_time":0,"fixed_time_at":"2019-04-10 21:24:13","weight":0,"view_num":"2","shared_num":0,"liked_num":1,"channel_id":0,"is_stuck":0,"status":1,"stuck_at":"2019-04-10 21:24:13","is_public":0,"img_num":2,"is_deleted":0,"updated_at":"2019-04-11 11:40:20","created_at":"2019-04-10 21:24:13"}
     * image : ["http://bj-test-1254383113.file.myqcloud.com/17674271978445.jpeg","http://bj-test-1254383113.file.myqcloud.com/79246923136961.jpg"]
     * extend : {"comment_num":"0","is_liked":"1","is_collect":"0"}
     * user_info : {"id":262,"nickname":"11","mobile":"13070172177","is_virtual":0,"app_user_id":"0","head_img_url":"https://education-1254383113.file.myqcloud.com/103982295236453.jpg"}
     */

    public ThreadBean thread;
    public ExtendBean extend;

    public ThreadBean getThread() {
        return thread;
    }

    public void setThread(ThreadBean thread) {
        this.thread = thread;
    }

    public ExtendBean getExtend() {
        return extend;
    }

    public void setExtend(ExtendBean extend) {
        this.extend = extend;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public List<String> getImage() {
        if (image == null) {
            return new ArrayList<>();
        }
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public UserInfoBean user_info;
    public List<String> image;

    @Override
    public int getItemLayoutId() {
        return R.layout.item_mail_content_header_layout;
    }

    @Override
    public void onBind(ViewHolder holder) {
        List<LocalMedia> selectList = new ArrayList<>();


        if (TextUtils.isEmpty(getThread().getTitle())) {
            holder.getView(R.id.tv_comm_content_title).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.tv_comm_content_title).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_comm_content_title, getThread().getTitle());
        }
        holder.setText(R.id.tv_comm_content, getThread().getContent());
        holder.setText(R.id.tv_comm_content_read_num, getThread().getView_num() + "浏览");
        holder.setText(R.id.tv_comm_content_time, getThread().getCreated_at());
        holder.setText(R.id.tv_comm_content_name, getUser_info().getNickname());
        holder.setText(R.id.tv_mail_content_all, String.format("全部评论(%s)", getExtend().getComment_num()));
        holder.setText(R.id.tv_comm_content_like_num, "点赞" + getThread().getLiked_num() + "");

        GlideUtils.loadImage(ApplicationsHelper.context(), getUser_info().getHead_img_url(), holder.getView(R.id.iv_comm_content_head));

        RecyclerView rv_comm_content_img_list = holder.getView(R.id.rv_comm_content_img_list);
        if (getThread().getImg_num() == 0) {
            rv_comm_content_img_list.setVisibility(View.GONE);
        } else {
            rv_comm_content_img_list.setVisibility(View.VISIBLE);
            if (getImage().size() == 1) {
                RequestOptions options = (new RequestOptions()).diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(com.blankj.utilcode.util.Utils.getApp()).asBitmap().load(getImage().get(0)).apply(options).into(new SimpleTarget<Bitmap>(480, 800) {
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        Bitmap scale = ImageUtils.scale(bitmap, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
                        int width = scale.getWidth();
                        int height = scale.getHeight();
                        rv_comm_content_img_list.setLayoutManager(new NineGridLayoutManager(ApplicationsHelper.context(), width, height));
                    }
                });
            } else {
                rv_comm_content_img_list.setLayoutManager(new NineGridLayoutManager(ApplicationsHelper.context()));
            }
            CommonAdapter mPic_adapter = new CommonAdapter<String>(ApplicationsHelper.context(), getImage().size() > 9 ? getImage().subList(0, 9) : getImage(), R.layout.item_comm_img_layout) {
                @Override
                public void convert(ViewHolder holder, String s) {
                    GlideUtils.loadImage(com.blankj.utilcode.util.Utils.getApp(), s, holder.getView(R.id.iv_comm_img_item));
                }
            };
            mPic_adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                    if (mPic_adapter.getDatas().size() > 0 && mPic_adapter.getDatas() != null) {
                        selectList.clear();
                        for (String s : getImage()) {
                            LocalMedia localMedia = new LocalMedia();
                            localMedia.setPath(s);
                            selectList.add(localMedia);
                        }

                        PicHelpter.openExternalPreview(position,selectList);
                    }
                }

                @Override
                public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                    return false;
                }
            });
            rv_comm_content_img_list.setAdapter(mPic_adapter);
        }

    }

    public static class ThreadBean {
        /**
         * id : 4616
         * user_id : 187
         * title : 了
         * content : 了
         * to_user_id : 0
         * to_group_id : 0
         * crawled_from :
         * is_crawled : 0
         * real_view_num : 2
         * virtual_view_num : 0
         * published_at : 昨天 21:24:13
         * is_fixed_time : 0
         * fixed_time_at : 2019-04-10 21:24:13
         * weight : 0
         * view_num : 2
         * shared_num : 0
         * liked_num : 1
         * channel_id : 0
         * is_stuck : 0
         * status : 1
         * stuck_at : 2019-04-10 21:24:13
         * is_public : 0
         * img_num : 2
         * is_deleted : 0
         * updated_at : 2019-04-11 11:40:20
         * created_at : 2019-04-10 21:24:13
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
        public int is_public;
        public int img_num;
        public int is_deleted;

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

        public String updated_at;
        public String created_at;
    }

    public static class ExtendBean {
        /**
         * comment_num : 0
         * is_liked : 1
         * is_collect : 0
         */

        public String comment_num;
        public String is_liked;

        public String getComment_num() {
            return comment_num == null ? "" : comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getIs_liked() {
            return is_liked == null ? "" : is_liked;
        }

        public void setIs_liked(String is_liked) {
            this.is_liked = is_liked;
        }

        public String getIs_collect() {
            return is_collect == null ? "" : is_collect;
        }

        public void setIs_collect(String is_collect) {
            this.is_collect = is_collect;
        }

        public String is_collect;
    }

    public static class UserInfoBean {
        /**
         * id : 262
         * nickname : 11
         * mobile : 13070172177
         * is_virtual : 0
         * app_user_id : 0
         * head_img_url : https://education-1254383113.file.myqcloud.com/103982295236453.jpg
         */

        public int id;
        public String nickname;

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

        public String mobile;
        public int is_virtual;
        public String app_user_id;
        public String head_img_url;
    }

}
