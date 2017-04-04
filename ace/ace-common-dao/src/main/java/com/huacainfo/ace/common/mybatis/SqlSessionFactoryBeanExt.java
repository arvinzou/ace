package com.huacainfo.ace.common.mybatis;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.huacainfo.ace.common.tools.XmlUtils;
 

/**
 * 继承该类,用来刷新mybatis.xml
 * 
 * @author zetee
 *
 */
public class SqlSessionFactoryBeanExt extends SqlSessionFactoryBean {

	public SqlSessionFactoryBeanExt() {
		//
	}

	@Override
	public SqlSessionFactory getObject() throws Exception {
		SqlSessionFactory sqlSessionFactory = super.getObject();
		initScaner(sqlSessionFactory);
		return sqlSessionFactory;
	}

	private synchronized void initScaner(SqlSessionFactory sqlSessionFactory) {
		if (mapperLocations==null||mapperLocations.length==0) {
			isLoadScaner = true;
			try {
				ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
				mapperLocations = resolver.getResources(packageSearchPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("=====================找到mybatis xml" + mapperLocations.length + " 个");
			startLoader(sqlSessionFactory);
		}
	}

	private static boolean isLoadScaner = false;
	private HashMap<String, Long> fileMapping = new HashMap<String, Long>();
	private static Scanner scanner = null;
	private static String packageSearchPath = "classpath*:/com/wegzoom/ace/*/dao/**/*.xml";
	private ScheduledExecutorService service = null;
	private SqlSessionFactory factory;
	private Map<String, String> ClearMap;
	private static Resource[] mapperLocations = null;

	public void startLoader(SqlSessionFactory factory) {
		scanner = new Scanner();
		this.factory = factory;
		service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(new Task(), 5, 5, TimeUnit.SECONDS);
	}

	class Task implements Runnable {
		@Override
		public void run() {
			try {
				scanner.refreshMapper();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings({ "rawtypes" })
	class Scanner {

		public void refreshMapper() {
			try {
				// SqlSessionFactory factory =
				// context.getBean(SqlSessionFactory.class);
				Configuration configuration = factory.getConfiguration();

				// step.1 扫描文件
				/*
				 * try { this.scanMapperXml(); } catch (IOException e) { return;
				 * }
				 */

				int index = 0;
				// step.2 判断是否有文件发生了变化
				List<Resource> changed = this.isChanged();
				if (changed.size() > 0) {
					System.out.println("=========检测到mapper有改变, 开始刷新===========");
					index++;
					// step.2.1 清理
					// getClearMap(changed);
					this.removeConfig(configuration);

					// step.2.2 重新加载
					for (Resource configLocation : changed) {
						try {
							System.out.println("正在刷新文件:" + configLocation.getFilename());
							XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configLocation.getInputStream(),
									configuration, configLocation.toString(), configuration.getSqlFragments());
							xmlMapperBuilder.parse();
						} catch (IOException e) {
							continue;
						}
					}
					System.out.println("================mapper刷新完毕^-^===============");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * private void scanMapperXml() throws IOException {
		 * if(this.mapperLocations==null) this.mapperLocations = new
		 * PathMatchingResourcePatternResolver().getResources(packageSearchPath)
		 * ; }
		 */

		private void removeConfig(Configuration configuration) throws Exception {
			Class<?> classConfig = configuration.getClass();
			clearMap(classConfig, configuration, "mappedStatements");
			clearMap(classConfig, configuration, "caches");
			clearMap(classConfig, configuration, "resultMaps");
			clearMap(classConfig, configuration, "parameterMaps");
			clearMap(classConfig, configuration, "keyGenerators");
			clearMap(classConfig, configuration, "sqlFragments");

			clearSet(classConfig, configuration, "loadedResources");

		}

		/**
		 * 需要清空的方法
		 * 
		 * @throws Exception
		 */
		private void getClearMap(List<Resource> changeResource) throws Exception {
			if (ClearMap != null && ClearMap.size() > 0)
				return;
			ClearMap = new HashMap<String, String>();
			// String packageName = "com.wz.manager.dao";
			ArrayList<String> classes = new ArrayList<String>();
			for (Resource resource : changeResource) {

				// String
				// fullpath=resource.getFile().getAbsolutePath();//.getFilename();
				Document doc = XmlUtils.ReadXml(resource.getFile());
				Element root = doc.getRootElement();
				String namespace = root.attributeValue("namespace");

				List<Element> selectElement = root.elements("select");
				for (Element one : selectElement) {
					ClearMap.put(namespace + "." + one.attributeValue("id"), "");
				}

				List<Element> insertElement = root.elements("insert");
				for (Element one : insertElement) {
					ClearMap.put(namespace + "." + one.attributeValue("id") + one.attributeValue("id"), "");
				}

				List<Element> updateElement = root.elements("update");
				for (Element one : updateElement) {
					ClearMap.put(namespace + "." + one.attributeValue("id") + one.attributeValue("id"), "");
				}

				List<Element> deleteElement = root.elements("delete");
				for (Element one : deleteElement) {
					ClearMap.put(namespace + "." + one.attributeValue("id") + one.attributeValue("id"), "");
				}

				List<Element> sqlElement = root.elements("sql");
				for (Element one : sqlElement) {
					ClearMap.put(namespace + "." + one.attributeValue("id") + one.attributeValue("id"), "");
				}

			}

		}

		private void getRecursiveInterfaces(Class pc) {

			Class[] classes = pc.getInterfaces();
			for (Class cls : classes) {
				if (cls.getName().equals("tk.mybatis.mapper.common.Mapper")) {
					Class[] interfaces2 = cls.getInterfaces();
					for (Class class2 : interfaces2) {
						Method[] methods = class2.getMethods();
						for (Method method : methods) {
							ClearMap.put(method.getName(), "");
						}
					}

				}
				getRecursiveInterfaces(cls);
			}
		}

		private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
			// 清除全部
			Field field = classConfig.getDeclaredField(fieldName);
			field.setAccessible(true);
			Map mapConfig = (Map) field.get(configuration);
			// System.out.println("清除:"+mapConfig.toString());
			mapConfig.clear();

			/*
			 * 清除选定
			 * 
			 * 
			 * Field field = classConfig.getDeclaredField(fieldName);
			 * field.setAccessible(true); Map mapConfig = (Map)
			 * field.get(configuration); Set keySet = mapConfig.keySet();
			 * List<String> removeList = new ArrayList<String>(); for (Object
			 * object : keySet) { String key = object.toString();
			 * //key=key.substring(key.lastIndexOf(".")+1);//
			 * //key=key.split("-")[0]; if (ClearMap.containsKey(key)) {
			 * removeList.add(object.toString());
			 * 
			 * } } for (String key : removeList) { mapConfig.remove(key);
			 * System.out.println("remove "+fieldName+" ="+key); }
			 */
		}

		private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
			Field field = classConfig.getDeclaredField(fieldName);
			field.setAccessible(true);
			Set setConfig = (Set) field.get(configuration);
			setConfig.clear();
		}

		private List<Resource> isChanged() throws IOException {
			boolean flag = false;

			List<Resource> changeList = new ArrayList<Resource>();
			for (Resource resource : mapperLocations) {
				String resourceName = resource.getFile().getAbsolutePath();// resource.getFilename();

				boolean addFlag = !fileMapping.containsKey(resourceName);// 此为新增标识

				// 修改文件:判断文件内容是否有变化
				Long compareFrame = fileMapping.get(resourceName);
				long lastFrame = resource.contentLength() + resource.lastModified();
				if (addFlag) {
					fileMapping.put(resourceName, Long.valueOf(lastFrame));// 文件内容帧值
				} else {
					boolean modifyFlag = (compareFrame.longValue() != lastFrame);// 此为修改标识

					// 修改时,存储文件
					if (modifyFlag) {
						fileMapping.put(resourceName, Long.valueOf(lastFrame));// 文件内容帧值
						flag = true;
						changeList.add(resource);
					}
				}

			}
			return changeList;
			// return flag;
		}
	}

	public String getPackageSearchPath() {
		return packageSearchPath;
	}

	// public void setPackageSearchPath(String packageSearchPath) {
	// this.packageSearchPath = packageSearchPath;
	// }

}
