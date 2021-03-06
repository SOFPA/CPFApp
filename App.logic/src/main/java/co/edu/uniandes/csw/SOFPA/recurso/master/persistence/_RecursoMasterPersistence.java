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

package co.edu.uniandes.csw.SOFPA.recurso.master.persistence;
import co.edu.uniandes.csw.SOFPA.comentario.logic.dto.ComentarioDTO;
import co.edu.uniandes.csw.SOFPA.recurso.master.persistence.entity.RecursopublicacionEntity;
import co.edu.uniandes.csw.SOFPA.comentario.persistence.converter.ComentarioConverter;
import co.edu.uniandes.csw.SOFPA.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.SOFPA.recurso.master.persistence.entity.Recursoestudiantes_recientesEntity;
import co.edu.uniandes.csw.SOFPA.usuario.persistence.converter.UsuarioConverter;
import co.edu.uniandes.csw.SOFPA.recurso.logic.dto.RecursoDTO;
import co.edu.uniandes.csw.SOFPA.recurso.master.logic.dto.RecursoMasterDTO;
import co.edu.uniandes.csw.SOFPA.recurso.master.persistence.api._IRecursoMasterPersistence;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.api.IRecursoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _RecursoMasterPersistence implements _IRecursoMasterPersistence {

  	@PersistenceContext(unitName="AppPU")
 
    protected EntityManager entityManager;
    
    @Inject
    protected IRecursoPersistence recursoPersistence;  

    public RecursoMasterDTO getRecurso(Long recursoId) {
        RecursoMasterDTO recursoMasterDTO = new RecursoMasterDTO();
        RecursoDTO recurso = recursoPersistence.getRecurso(recursoId);
        recursoMasterDTO.setRecursoEntity(recurso);
        recursoMasterDTO.setListpublicacion(getRecursopublicacionEntityList(recursoId));
        recursoMasterDTO.setListestudiantes_recientes(getRecursoestudiantes_recientesEntityList(recursoId));
        return recursoMasterDTO;
    }

    public RecursopublicacionEntity createRecursopublicacionEntity(RecursopublicacionEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteRecursopublicacionEntity(Long recursoId, Long publicacionId) {
        Query q = entityManager.createNamedQuery("RecursopublicacionEntity.deleteRecursopublicacionEntity");
        q.setParameter("recursoId", recursoId);
        q.setParameter("publicacionId", publicacionId);
        q.executeUpdate();
    }

    public List<ComentarioDTO> getRecursopublicacionEntityList(Long recursoId) {
        ArrayList<ComentarioDTO> resp = new ArrayList<ComentarioDTO>();
        Query q = entityManager.createNamedQuery("RecursopublicacionEntity.getByMasterId");
        q.setParameter("recursoId",recursoId);
        List<RecursopublicacionEntity> qResult =  q.getResultList();
        for (RecursopublicacionEntity entity : qResult) { 
            if(entity.getPublicacionIdEntity()==null){
                entityManager.refresh(entity);
            }
            resp.add(ComentarioConverter.entity2PersistenceDTO(entity.getPublicacionIdEntity()));
        }
        return resp;
    }
    public Recursoestudiantes_recientesEntity createRecursoestudiantes_recientesEntity(Recursoestudiantes_recientesEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteRecursoestudiantes_recientesEntity(Long recursoId, Long estudiantes_recientesId) {
        Query q = entityManager.createNamedQuery("Recursoestudiantes_recientesEntity.deleteRecursoestudiantes_recientesEntity");
        q.setParameter("recursoId", recursoId);
        q.setParameter("estudiantes_recientesId", estudiantes_recientesId);
        q.executeUpdate();
    }

    public List<UsuarioDTO> getRecursoestudiantes_recientesEntityList(Long recursoId) {
        ArrayList<UsuarioDTO> resp = new ArrayList<UsuarioDTO>();
        Query q = entityManager.createNamedQuery("Recursoestudiantes_recientesEntity.getByMasterId");
        q.setParameter("recursoId",recursoId);
        List<Recursoestudiantes_recientesEntity> qResult =  q.getResultList();
        for (Recursoestudiantes_recientesEntity entity : qResult) { 
            if(entity.getEstudiantes_recientesIdEntity()==null){
                entityManager.refresh(entity);
            }
            resp.add(UsuarioConverter.entity2PersistenceDTO(entity.getEstudiantes_recientesIdEntity()));
        }
        return resp;
    }

}
