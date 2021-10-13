package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  ProductServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� //@Test : �׽�Ʈ ���� �ҽ� ����
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdNo(1111);
		product.setProdName("testProductName");
		product.setProdDetail("testDetail");
		product.setPrice(123456);
		product.setFileName("testFile");
		product.setManuDate("21-10-06");
		//product.setRegDate("2021-10-05");
		
		productService.addProduct(product);
		
//		product = productService.getProduct(1111);

		//==> console Ȯ��
		//System.out.println(product);
		
		//==> API Ȯ��
//		Assert.assertEquals(1111, product.getProdNo());
//		Assert.assertEquals("testProductName", product.getProdName());
//		Assert.assertEquals("testDetail", product.getProdDetail());
//		Assert.assertEquals(123456, product.getPrice());
//		Assert.assertEquals("testFile", product.getFileName());
//		Assert.assertEquals("21-10-06", product.getManuDate());
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
	
		//==> �ʿ��ϴٸ�...
		Product product = new Product();
//		product.setProdNo(1111);
//		product.setProdName("testProductName");
//		product.setProdDetail("testDetail");
//		product.setPrice(123456);
//		product.setFileName("testFile");
//		product.setManuDate("2021-10-06");
		
		product = productService.getProduct(1111);

		//==> console Ȯ��
		//System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals(1111, product.getProdNo());
		Assert.assertEquals("testProductName", product.getProdName());
		Assert.assertEquals("testDetail", product.getProdDetail());
		Assert.assertEquals(123456, product.getPrice());
		Assert.assertEquals("testFile", product.getFileName());
		Assert.assertEquals("21-10-06", product.getManuDate());

		Assert.assertNotNull(productService.getProduct(10000));
		Assert.assertNotNull(productService.getProduct(10001));
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(1111);
		Assert.assertNotNull(product);
		
		Assert.assertEquals(1111, product.getProdNo());
		Assert.assertEquals("testProductName", product.getProdName());
		Assert.assertEquals("testDetail", product.getProdDetail());
		Assert.assertEquals(123456, product.getPrice());

		product.setProdNo(1111);
		product.setProdName("testProductName22");
		product.setProdDetail("testDetail33");
		product.setPrice(123456789);
		
		productService.updateProduct(product);
		
		product = productService.getProduct(1111);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		//System.out.println(product);
			
		//==> API Ȯ��
		Assert.assertEquals(1111, product.getProdNo());
		Assert.assertEquals("testProductName22", product.getProdName());
		Assert.assertEquals("testDetail33", product.getProdDetail());
		Assert.assertEquals(123456789, product.getPrice());
	 }
	 
	
	
	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProductId() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("100");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(5, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 @Test
	 public void testGetProductListByProductName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("�ζ���");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}