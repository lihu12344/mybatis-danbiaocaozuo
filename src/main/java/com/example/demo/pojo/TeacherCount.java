package com.example.demo.pojo;

import java.util.Objects;

public class TeacherCount {

    private Integer age;
    private Integer count;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherCount)) return false;
        TeacherCount that = (TeacherCount) o;
        return Objects.equals(getAge(), that.getAge()) &&
                Objects.equals(getCount(), that.getCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getCount());
    }
}
