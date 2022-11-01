package com.qa.JsonReader;

import java.util.Iterator;
import java.util.List;

import POjO.UserRole;
import libapiresthelper.JsonUtil;

public class JsonReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path="./src/test/resources/JsonFiles/UserRole.json";
		List<UserRole> users=JsonUtil.getUserListfromJson(path);
		
		Iterator<UserRole> itr=users.iterator();
		
		synchronized(users)
		{
			while(itr.hasNext())
			{
				UserRole user=itr.next();
				System.out.print(user.getEuid()+":"+user.getPassword());
				System.out.println();
			}
		}
		
		

	}

}
