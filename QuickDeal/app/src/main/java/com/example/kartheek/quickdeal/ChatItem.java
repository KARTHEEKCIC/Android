package com.example.kartheek.quickdeal;

/**
 * Created by kartheek on 20/4/17.
 */

public class ChatItem {

    private String mChatTitle;

    private int mUnreadMessages;

    private String mText;

    private String mTime;

    private boolean mDealDone;

    public ChatItem(String ChatTitle, int UnreadMessages, String Text, String Time, Boolean DealDone) {
        mChatTitle = ChatTitle;
        mUnreadMessages = UnreadMessages;
        mText = Text;
        mTime = Time;
        mDealDone = DealDone;
    }

    // getters

    public boolean getmDealDone(){ return mDealDone; }

    public String getmChatTitle() {
        return mChatTitle;
    }

    public int getmUnreadMessages() {
        return mUnreadMessages;
    }

    public String getmText() {
        return mText;
    }

    public String getmTime() {
        return mTime;
    }

    // setters

    public void setmDealDone(boolean DealDone) {
        mDealDone = DealDone;
    }

    public void setmChatTitle(String ChatTitle) {
        mChatTitle = ChatTitle;
    }

    public void setmUnreadMessages(int UnreadMessages) {
        mUnreadMessages = UnreadMessages;
    }

    public void setmText(String Text) {
        mText = Text;
    }

    public void setmTime(String Time) {
        mTime = Time;
    }
}
