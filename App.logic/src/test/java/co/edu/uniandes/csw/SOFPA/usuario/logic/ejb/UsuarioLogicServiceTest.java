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

package co.edu.uniandes.csw.SOFPA.usuario.logic.ejb;

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


import co.edu.uniandes.csw.SOFPA.usuario.logic.dto.UsuarioPageDTO;
import co.edu.uniandes.csw.SOFPA.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.SOFPA.usuario.logic.api.IUsuarioLogicService;
import co.edu.uniandes.csw.SOFPA.usuario.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.SOFPA.usuario.persistence.api.IUsuarioPersistence;
import co.edu.uniandes.csw.SOFPA.usuario.persistence.entity.UsuarioEntity;
import co.edu.uniandes.csw.SOFPA.usuario.persistence.converter.UsuarioConverter;
import static co.edu.uniandes.csw.SOFPA.util._TestUtil.*;

@RunWith(Arquillian.class)
public class UsuarioLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(UsuarioLogicService.class.getPackage())
				.addPackage(IUsuarioLogicService.class.getPackage())
				.addPackage(UsuarioPersistence.class.getPackage())
				.addPackage(UsuarioEntity.class.getPackage())
				.addPackage(IUsuarioPersistence.class.getPackage())
                .addPackage(UsuarioDTO.class.getPackage())
                .addPackage(UsuarioConverter.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IUsuarioLogicService usuarioLogicService;
	
	@Inject
	private IUsuarioPersistence usuarioPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<UsuarioDTO> dtos=usuarioPersistence.getUsuarios();
		for(UsuarioDTO dto:dtos){
			usuarioPersistence.deleteUsuario(dto.getId());
		}
	}

	private List<UsuarioDTO> data=new ArrayList<UsuarioDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			UsuarioDTO pdto=new UsuarioDTO();
			pdto.setCantidadVisitas(generateRandom(Integer.class));
			pdto.setCarrera(generateRandom(String.class));
			pdto.setCursoActual(generateRandom(String.class));
			pdto.setCodigo(generateRandom(String.class));
			pdto.setSeccMagistral(generateRandom(String.class));
			pdto.setSeccComplementaria(generateRandom(String.class));
			pdto.setName(generateRandom(String.class));
			pdto.setApellidos(generateRandom(String.class));
			pdto.setContrasenia(generateRandom(String.class));
			pdto.setCorreo(generateRandom(String.class));
			pdto.setRol(generateRandom(String.class));
			pdto=usuarioPersistence.createUsuario(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createUsuarioTest(){
		UsuarioDTO ldto=new UsuarioDTO();
		ldto.setCantidadVisitas(generateRandom(Integer.class));
		ldto.setCarrera(generateRandom(String.class));
		ldto.setCursoActual(generateRandom(String.class));
		ldto.setCodigo(generateRandom(String.class));
		ldto.setSeccMagistral(generateRandom(String.class));
		ldto.setSeccComplementaria(generateRandom(String.class));
		ldto.setName(generateRandom(String.class));
		ldto.setApellidos(generateRandom(String.class));
		ldto.setContrasenia(generateRandom(String.class));
		ldto.setCorreo(generateRandom(String.class));
		ldto.setRol(generateRandom(String.class));
		
		
		UsuarioDTO result=usuarioLogicService.createUsuario(ldto);
		
		Assert.assertNotNull(result);
		
		UsuarioDTO pdto=usuarioPersistence.getUsuario(result.getId());
		
		Assert.assertEquals(ldto.getCantidadVisitas(), pdto.getCantidadVisitas());	
		Assert.assertEquals(ldto.getCarrera(), pdto.getCarrera());	
		Assert.assertEquals(ldto.getCursoActual(), pdto.getCursoActual());	
		Assert.assertEquals(ldto.getCodigo(), pdto.getCodigo());	
		Assert.assertEquals(ldto.getSeccMagistral(), pdto.getSeccMagistral());	
		Assert.assertEquals(ldto.getSeccComplementaria(), pdto.getSeccComplementaria());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getApellidos(), pdto.getApellidos());	
		Assert.assertEquals(ldto.getContrasenia(), pdto.getContrasenia());	
		Assert.assertEquals(ldto.getCorreo(), pdto.getCorreo());	
		Assert.assertEquals(ldto.getRol(), pdto.getRol());	
	}
	
	@Test
	public void getUsuariosTest(){
		List<UsuarioDTO> list=usuarioLogicService.getUsuarios();
		Assert.assertEquals(list.size(), data.size());
        for(UsuarioDTO ldto:list){
        	boolean found=false;
            for(UsuarioDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getUsuarioTest(){
		UsuarioDTO pdto=data.get(0);
		UsuarioDTO ldto=usuarioLogicService.getUsuario(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getCantidadVisitas(), ldto.getCantidadVisitas());
		Assert.assertEquals(pdto.getCarrera(), ldto.getCarrera());
		Assert.assertEquals(pdto.getCursoActual(), ldto.getCursoActual());
		Assert.assertEquals(pdto.getCodigo(), ldto.getCodigo());
		Assert.assertEquals(pdto.getSeccMagistral(), ldto.getSeccMagistral());
		Assert.assertEquals(pdto.getSeccComplementaria(), ldto.getSeccComplementaria());
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getApellidos(), ldto.getApellidos());
		Assert.assertEquals(pdto.getContrasenia(), ldto.getContrasenia());
		Assert.assertEquals(pdto.getCorreo(), ldto.getCorreo());
		Assert.assertEquals(pdto.getRol(), ldto.getRol());
        
	}
	
	@Test
	public void deleteUsuarioTest(){
		UsuarioDTO pdto=data.get(0);
		usuarioLogicService.deleteUsuario(pdto.getId());
        UsuarioDTO deleted=usuarioPersistence.getUsuario(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateUsuarioTest(){
		UsuarioDTO pdto=data.get(0);
		
		UsuarioDTO ldto=new UsuarioDTO();
		ldto.setId(pdto.getId());
		ldto.setCantidadVisitas(generateRandom(Integer.class));
		ldto.setCarrera(generateRandom(String.class));
		ldto.setCursoActual(generateRandom(String.class));
		ldto.setCodigo(generateRandom(String.class));
		ldto.setSeccMagistral(generateRandom(String.class));
		ldto.setSeccComplementaria(generateRandom(String.class));
		ldto.setName(generateRandom(String.class));
		ldto.setApellidos(generateRandom(String.class));
		ldto.setContrasenia(generateRandom(String.class));
		ldto.setCorreo(generateRandom(String.class));
		ldto.setRol(generateRandom(String.class));
		
		
		usuarioLogicService.updateUsuario(ldto);
		
		
		UsuarioDTO resp=usuarioPersistence.getUsuario(pdto.getId());
		
		Assert.assertEquals(ldto.getCantidadVisitas(), resp.getCantidadVisitas());	
		Assert.assertEquals(ldto.getCarrera(), resp.getCarrera());	
		Assert.assertEquals(ldto.getCursoActual(), resp.getCursoActual());	
		Assert.assertEquals(ldto.getCodigo(), resp.getCodigo());	
		Assert.assertEquals(ldto.getSeccMagistral(), resp.getSeccMagistral());	
		Assert.assertEquals(ldto.getSeccComplementaria(), resp.getSeccComplementaria());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getApellidos(), resp.getApellidos());	
		Assert.assertEquals(ldto.getContrasenia(), resp.getContrasenia());	
		Assert.assertEquals(ldto.getCorreo(), resp.getCorreo());	
		Assert.assertEquals(ldto.getRol(), resp.getRol());	
	}
	
	@Test
	public void getUsuarioPaginationTest(){
		
		UsuarioPageDTO dto1=usuarioLogicService.getUsuarios(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		UsuarioPageDTO dto2=usuarioLogicService.getUsuarios(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(UsuarioDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(UsuarioDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(UsuarioDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(UsuarioDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        UsuarioPageDTO dto3=usuarioLogicService.getUsuarios(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(UsuarioDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(UsuarioDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}