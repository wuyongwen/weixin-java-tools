package me.chanjar.weixin.mp.bean;

import java.io.Serializable;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

public class WxMpMaterialArticleUpdate implements Serializable {

  private String mediaId;
  private int index;
  private WxMpMaterialNewsArticle articles;

  public String getMediaId() {
    return mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public WxMpMaterialNewsArticle getArticles() {
    return articles;
  }

  public void setArticles(WxMpMaterialNewsArticle articles) {
    this.articles = articles;
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Override
  public String toString() {
    return "WxMpMaterialArticleUpdate [" + "mediaId=" + mediaId + ", index=" + index + ", articles=" + articles + "]";
  }
}
