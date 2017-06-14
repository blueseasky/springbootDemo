package com.blueseasky.springboot;

import com.blueseasky.springboot.domain.User;
import com.blueseasky.springboot.facade.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ApplicationTests {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception{

		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void testUserController() throws Exception{

		RequestBuilder request = null;

		//1 get查询users列表
		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

		//2 post 提交一个user
		request = post("/users/")
				.param("id", "1")
				.param("name", "工程师")
				.param("age", "20");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));


		//3 get 获取user列表，应该有刚才插入的数据
		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"工程师\",\"age\":20}]")));

		//4 put 修改id为1的user
		request = put("/users/1")
				.param("name", "开发工程师")
				.param("age", "30");

		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));

		//5 get一个id为1的user
		request = get("/users/1");
		mvc.perform(request)
				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"开发工程师\",\"age\":30}")));

		//6 del 删除id为1的user
		request = delete("/users/1");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));

		//7 get查一下users列表，应该为空
		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));


		/*//8 post 以@RequestBody 插入一个user，
		request = post("/users/body")
				.param("id", "2")
				.param("name", "高级开发工程师")
				.param("age", "40")
				.contentType("application/json");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		//9 get一个id为2的user
		request = get("/users/1");
		mvc.perform(request)
				.andExpect(content().string(equalTo("{\"id\":2,\"name\":\"高级开发工程师\",\"age\":40}")));
*/
	}

	@Test
	public void testUserControllerByJson() throws  Exception{

		String add_url = "http://localhost:8888/users/body";
		URL url = new URL(add_url);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type","application/json");
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		JSONObject obj = new JSONObject();

//		obj.put("id", "2");
//		obj.put("name", "高级工程师");
//		obj.put("age", "40");
//		out.writeBytes(obj.toString());

		User user = new User(2L,"高级工程师",40);
		ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(user);
		out.writeBytes(jsonStr);

		out.flush();
		out.close();


		int resultCode = connection.getResponseCode();




		if (HttpURLConnection.HTTP_OK == resultCode){

			StringBuffer sb = new StringBuffer();
			String readline = new String();

			BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			while((readline = responseReader.readLine()) != null){

				sb.append(readline).append("\n");
			}
			responseReader.close();

			System.out.println(sb.toString());

		}else {

			System.out.println("调用失败，返回错误码为："+ resultCode);
		}




	}

	@Test
	public void contextLoads() {


	}

}
