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

import co.edu.uniandes.csw.SOFPA.recurso.logic.dto.RecursoDTO;
import co.edu.uniandes.csw.SOFPA.recurso.logic.dto.RecursoPageDTO;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.api.IRecursoPersistence;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.converter.RecursoConverter;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.converter._RecursoConverter;
import java.io.File;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.Query;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.converter.RecursoConverter;
import co.edu.uniandes.csw.SOFPA.recurso.persistence.entity.RecursoEntity;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

@Default
@Stateless 
@LocalBean
public class RecursoPersistence extends _RecursoPersistence  implements IRecursoPersistence {

    @SuppressWarnings("unchecked")
    public RecursoPageDTO getRecursosPorTema(String tema)
    {
        Query count = entityManager.createQuery("select count(r) from RecursoEntity r");
        Long regCount = 0L;
        regCount = Long.parseLong(count.getSingleResult().toString());
        Query q = entityManager.createQuery("SELECT u FROM RecursoEntity WHERE u.tema like :tema");
        q.setParameter("Parameter", "%"+tema+"%");
        
        RecursoPageDTO response = new RecursoPageDTO();
        response.setTotalRecords(regCount);
        response.setRecords(RecursoConverter.entity2PersistenceDTOList(q.getResultList()));
        return response;
    }
    
    @SuppressWarnings("unchecked")
    public String createRecurso(InputStream fileIS, FormDataContentDisposition content){
        String fileName = content.getFileName();
       String filePath = "C://CPFApp/Documentos/" + fileName;
       try{
           OutputStream fileOS = new FileOutputStream(filePath);
	   System.out.println("***** fileName " + content.getFileName());
           int read = 0;
           final byte[] bytes = new byte[1024];
           while((read = fileIS.read(bytes))!=-1){
               fileOS.write(bytes, 0, read);
           }
       }catch(FileNotFoundException e){
           return "El archivo no existe o la ruta esta mal escrita.";
       }catch(IOException e){
           return " =( Error critico al subir un archivo, por favor comuniquese con el desarrollador.";
       }
       return "Archivo subido correctamente";
    }
}