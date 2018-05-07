package com.lesson.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.lession.model.Student;
import com.lession.utils.JedisPoolUtil;
import com.lession.utils.StudentUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
public class StudentDao {
	/**
	 * 分页列表
	 * @param currentPage
	 * @return
	 */
	public List<Student> studentList(String currentPage){
		int cp = Integer.parseInt(currentPage);
		List<Student> students = new ArrayList<>();
		Jedis jedis = JedisPoolUtil.getJedis();
		Set<Tuple> stus = jedis.zrevrangeByScoreWithScores("students", 100, 0, (cp-1)*10, 10);
		jedis.close();
		for (Iterator<Tuple> iterator = stus.iterator(); iterator.hasNext();) {
			Tuple tuple = (Tuple) iterator.next();
			String data = tuple.getElement();
			double score = tuple.getScore();
			Student student = StudentUtil.dsToStudent(data, (int)score);
			students.add(student);
		}
		return students;
		
	}
	/**
	 * 添加
	 * @param data
	 * @param score
	 */
	public void studentAdd(String data, double score) {
		Jedis jedis = JedisPoolUtil.getJedis();
    	jedis.zadd("students", score, data);
    	jedis.close();
		
	}
	/**
	 * 根据score和ip找到对应的唯一学生数据
	 * @param score
	 * @param id
	 * @return 返回student对象
	 */
	public Student findByScoreAndId(double score,String id) {
		Student student = new Student();
		Jedis jedis = JedisPoolUtil.getJedis();
		Set<String> studentByScore = jedis.zrangeByScore("students", score, score);
		jedis.close();
		for (String str : studentByScore) {
			if(id.equals(str.split(",")[0])) {
				student = StudentUtil.dsToStudent(str, score);
			}
		}
		return student;
	}
	/**
	 * 删除
	 * @param data
	 */
	public void studentRem(String data) {
		Jedis jedis = JedisPoolUtil.getJedis();
		jedis.zrem("students", data);
		jedis.close();
	}
	/**
	 * 根据条数返回总页数
	 * @return
	 */
	public long studentSumPages() {
		Jedis jedis = JedisPoolUtil.getJedis();
		Long rows = jedis.zcard("students");
		jedis.close();
		long totalPage = rows%10==0 ? rows/10:rows/10+1;
		return totalPage;
	}
}
