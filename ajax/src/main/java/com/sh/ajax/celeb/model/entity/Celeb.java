package com.sh.ajax.celeb.model.entity;

public class Celeb {
    private long id;
    private String name;
    private String profile;
    private Type type;

    public Celeb() {
    }

    public Celeb(long id, String name, String profile, Type type) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        //profile이 null이면 기본 프로필로 처리하기
        if(profile==null)
            return "default.png";
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Celeb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profile='" + profile + '\'' +
                ", type=" + type +
                '}';
    }
}
