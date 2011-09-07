package com.liuapps.logserver;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable { // 
  private String tag;
  private String text;

  public Message(Parcel in) { // 
    tag = in.readString();
    text = in.readString();
  }

  public void writeToParcel(Parcel out, int flags) { // 
    out.writeString(tag);
    out.writeString(text);
  }

  public int describeContents() { // 
    return 0;
  }

  public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() { // 

    public Message createFromParcel(Parcel source) {
      return new Message(source);
    }

    public Message[] newArray(int size) {
      return new Message[size];
    }

  };

  // Setters and Getters 
  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}