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

package co.edu.uniandes.csw.SOFPA.recurso.master.persistence.entity;

import co.edu.uniandes.csw.SOFPA.comentario.persistence.entity.ComentarioEntity;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.entity.RecursoEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(RecursopublicacionEntityId.class)
@NamedQueries({
    @NamedQuery(name = "RecursopublicacionEntity.getByMasterId", query = "SELECT  u FROM RecursopublicacionEntity u WHERE u.recursoId=:recursoId"),
    @NamedQuery(name = "RecursopublicacionEntity.deleteRecursopublicacionEntity", query = "DELETE FROM RecursopublicacionEntity u WHERE u.recursoId=:recursoId and  u.publicacionId=:publicacionId")
})
public class RecursopublicacionEntity implements Serializable {

    @Id
    @Column(name = "recursoId")
    private Long recursoId;
    @Id
    @Column(name = "publicacionId")
    private Long publicacionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "recursoId", referencedColumnName = "id")
    @JoinFetch
    private ComentarioEntity recursoIdEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "publicacionId", referencedColumnName = "id")
    @JoinFetch
    private ComentarioEntity publicacionIdEntity; 

    public RecursopublicacionEntity() {
    }

    public RecursopublicacionEntity(Long recursoId, Long publicacionId) {
        this.recursoId =recursoId;
        this.publicacionId = publicacionId;
    }

    public Long getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(Long recursoId) {
        this.recursoId = recursoId;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public ComentarioEntity getRecursoIdEntity() {
        return recursoIdEntity;
    }

    public void setRecursoIdEntity(ComentarioEntity recursoIdEntity) {
        this.recursoIdEntity = recursoIdEntity;
    }

    public ComentarioEntity getPublicacionIdEntity() {
        return publicacionIdEntity;
    }

    public void setPublicacionIdEntity(ComentarioEntity publicacionIdEntity) {
        this.publicacionIdEntity = publicacionIdEntity;
    }

}
