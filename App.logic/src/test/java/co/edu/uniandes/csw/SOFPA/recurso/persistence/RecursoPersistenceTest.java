/* ========================================================================
 * Copyright 2014 SOFPA
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 SOFPA

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201410152247

*/

package co.edu.uniandes.csw.SOFPA.recurso.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.SOFPA.recurso.logic.dto.RecursoPageDTO;
import co.edu.uniandes.csw.SOFPA.recurso.logic.dto.RecursoDTO;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.api.IRecursoPersistence;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.entity.RecursoEntity;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.converter.RecursoConverter;
import static co.edu.uniandes.csw.SOFPA.util._TestUtil.*;

@RunWith(Arquillian.class)
public class RecursoPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(RecursoPersistence.class.getPackage())
				.addPackage(RecursoEntity.class.getPackage())
				.addPackage(IRecursoPersistence.class.getPackage())
                .addPackage(RecursoDTO.class.getPackage())
                .addPackage(RecursoConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IRecursoPersistence recursoPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from RecursoEntity").executeUpdate();
	}

	private List<RecursoEntity> data=new ArrayList<RecursoEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			RecursoEntity entity=new RecursoEntity();
			entity.setTema(generateRandom(String.class));
			entity.setTipo(generateRandom(String.class));
			entity.setCurso(generateRandom(String.class));
			entity.setDificultad(generateRandom(Integer.class));
			entity.setSemestre(generateRandom(String.class));
			entity.setAvalado(generateRandom(Boolean.class));
			entity.setCantidadVisitas(generateRandom(Integer.class));
			entity.setUltimaFechaVisita(generateRandom(Date.class));
			entity.setName(generateRandom(String.class));
			entity.setURL(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createRecursoTest(){
		RecursoDTO dto=new RecursoDTO();
		dto.setTema(generateRandom(String.class));
		dto.setTipo(generateRandom(String.class));
		dto.setCurso(generateRandom(String.class));
		dto.setDificultad(generateRandom(Integer.class));
		dto.setSemestre(generateRandom(String.class));
		dto.setAvalado(generateRandom(Boolean.class));
		dto.setCantidadVisitas(generateRandom(Integer.class));
dto.setUltimaFechaVisita(generateRandomDate());
		dto.setName(generateRandom(String.class));
		dto.setURL(generateRandom(String.class));
		
		RecursoDTO result=recursoPersistence.createRecurso(dto);
		
		Assert.assertNotNull(result);
		
		RecursoEntity entity=em.find(RecursoEntity.class, result.getId());
		
		Assert.assertEquals(dto.getTema(), entity.getTema());
		Assert.assertEquals(dto.getTipo(), entity.getTipo());
		Assert.assertEquals(dto.getCurso(), entity.getCurso());
		Assert.assertEquals(dto.getDificultad(), entity.getDificultad());
		Assert.assertEquals(dto.getSemestre(), entity.getSemestre());
		Assert.assertEquals(dto.getAvalado(), entity.getAvalado());
		Assert.assertEquals(dto.getCantidadVisitas(), entity.getCantidadVisitas());
Assert.assertEquals(parseDate(dto.getUltimaFechaVisita()), entity.getUltimaFechaVisita());	
		Assert.assertEquals(dto.getName(), entity.getName());
		Assert.assertEquals(dto.getURL(), entity.getURL());
	}
	
	@Test
	public void getRecursosTest(){
		List<RecursoDTO> list=recursoPersistence.getRecursos();
		Assert.assertEquals(list.size(), data.size());
        for(RecursoDTO dto:list){
        	boolean found=false;
            for(RecursoEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getRecursoTest(){
		RecursoEntity entity=data.get(0);
		RecursoDTO dto=recursoPersistence.getRecurso(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getTema(), dto.getTema());
		Assert.assertEquals(entity.getTipo(), dto.getTipo());
		Assert.assertEquals(entity.getCurso(), dto.getCurso());
		Assert.assertEquals(entity.getDificultad(), dto.getDificultad());
		Assert.assertEquals(entity.getSemestre(), dto.getSemestre());
		Assert.assertEquals(entity.getAvalado(), dto.getAvalado());
		Assert.assertEquals(entity.getCantidadVisitas(), dto.getCantidadVisitas());
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getURL(), dto.getURL());
        
	}
	
	@Test
	public void deleteRecursoTest(){
		RecursoEntity entity=data.get(0);
		recursoPersistence.deleteRecurso(entity.getId());
        RecursoEntity deleted=em.find(RecursoEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateRecursoTest(){
		RecursoEntity entity=data.get(0);
		
		RecursoDTO dto=new RecursoDTO();
		dto.setId(entity.getId());
		dto.setTema(generateRandom(String.class));
		dto.setTipo(generateRandom(String.class));
		dto.setCurso(generateRandom(String.class));
		dto.setDificultad(generateRandom(Integer.class));
		dto.setSemestre(generateRandom(String.class));
		dto.setAvalado(generateRandom(Boolean.class));
		dto.setCantidadVisitas(generateRandom(Integer.class));
dto.setUltimaFechaVisita(generateRandomDate());
		dto.setName(generateRandom(String.class));
		dto.setURL(generateRandom(String.class));
		
		
		recursoPersistence.updateRecurso(dto);
		
		
		RecursoEntity resp=em.find(RecursoEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getTema(), resp.getTema());	
		Assert.assertEquals(dto.getTipo(), resp.getTipo());	
		Assert.assertEquals(dto.getCurso(), resp.getCurso());	
		Assert.assertEquals(dto.getDificultad(), resp.getDificultad());	
		Assert.assertEquals(dto.getSemestre(), resp.getSemestre());	
		Assert.assertEquals(dto.getAvalado(), resp.getAvalado());	
		Assert.assertEquals(dto.getCantidadVisitas(), resp.getCantidadVisitas());	
Assert.assertEquals(parseDate(dto.getUltimaFechaVisita()), resp.getUltimaFechaVisita());
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getURL(), resp.getURL());	
	}
	
	@Test
	public void getRecursoPaginationTest(){
		//Page 1
		RecursoPageDTO dto1=recursoPersistence.getRecursos(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        RecursoPageDTO dto2=recursoPersistence.getRecursos(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(RecursoDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(RecursoEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(RecursoDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(RecursoEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
}