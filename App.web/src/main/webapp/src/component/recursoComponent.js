
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
define(['component/_recursoComponent', 'model/recursosPorAvalarModel'], function() {
    App.Component.RecursoComponent = App.Component._RecursoComponent.extend({
        listModel: App.Model.RecursoList,
        postInit: function() { 
            this.toolbarComponent.hideButton('print');
            
            this.listComponent.addAction({
                name: 'view',
                displayName: 'View',
                show: true,
                icon: ''
            }, this.mostrarRecurso,this);
            this.toolbarComponent.addButton({
                name: 'exec-search',
                idsplayName: 'Search',
                icon: 'glyphicon-search',
                show: false
            },
            this.execSearch,
                    this);
                
            

            this.toolbarComponent.addButton({
                name: 'avalar',
                displayName: 'Avalar',
                icon: '',
                show: true
            },
            function() {
                self.componentController.hacerLista();
            },
                    this);

            this.toolbarComponent.addButton({
                name: 'exec-avalar',
                displayName: 'Avalar',
                icon: 'glyphicon-thumbs-up',
                show: false
            },
            this.execAvalar,
                    this);

            this.toolbarComponent.addButton({
                name: 'cancel-search',
                idsplayName: 'Cancel',
                icon: 'glyphicon-remove-sign',
                show: false
            },
            function() {
                this.toolbarComponent.showButton('create');
                this.toolbarComponent.showButton('refresh');
                this.toolbarComponent.hideButton('print');
                this.toolbarComponent.showButton('search');
                this.toolbarComponent.hideButton('exec-search');
                this.toolbarComponent.hideButton('cancel-search');
                this.toolbarComponent.render();
                this.componentController.list(null, this.list, this);
            },
                    this);
        this.toolbarComponent.addButton({
                name: 'exec-search2',
                displayName: 'BuscarporNombre',
               icon: 'glyphicon-search',
              show: false
           },
            this.execSearch,
             this);
           this.toolbarComponent.addButton({
               name: 'cancel-search2',
               displayName: 'Cancelar Busqueda',
                 icon: 'glyphicon-remove-sign',
                 show: false
            },
             function(){
               this.toolbarComponent.showButton('create');
                this.toolbarComponent.showButton('refresh');
                this.toolbarComponent.hideButton('print');
               this.toolbarComponent.showButton('search');
                 this.toolbarComponent.hideButton('cancel-search2');
                 this.toolbarComponent.hideButton('exec-search2');
                this.toolbarComponent.render();
                 this.componentController.list(null, this.list, this);
           },
             this);  
         },
        create: function()
        {
            this.toolbarComponent.showButton('create');
            this.toolbarComponent.hideButton('refresh');
            this.toolbarComponent.hideButton('print');
            this.toolbarComponent.hideButton('search');
            this.toolbarComponent.hideButton('exec-search');
            this.toolbarComponent.showButton('cancel-search');
            this.toolbarComponent.showButton('save');
            this.toolbarComponent.render();
            this.componentController.create();
        },
        search: function()
        {
            this.toolbarComponent.hideButton('create');
            this.toolbarComponent.hideButton('save');
            this.toolbarComponent.hideButton('cancel');
            this.toolbarComponent.hideButton('refresh');
            this.toolbarComponent.hideButton('print');
            this.toolbarComponent.hideButton('search');
            this.toolbarComponent.showButton('exec-search');
            this.toolbarComponent.showButton('cancel-search');
            this.toolbarComponent.render();
            this.componentController.create();
        },
        execSearch: function() {
            this.toolbarComponent.showButton('create');
            this.toolbarComponent.showButton('refresh');
            this.toolbarComponent.showButton('print');
            this.toolbarComponent.showButton('search');
            this.toolbarComponent.hideButton('exec-search');
            this.toolbarComponent.hideButton('cancel-search');
            this.toolbarComponent.render();
            this.componentController.recursoSearch(this.list, this);
        },
        execAvalar: function() {
            this.toolbarComponent.showButton('create');
            this.toolbarComponent.showButton('refresh');
            this.toolbarComponent.showButton('print');
            this.toolbarComponent.showButton('search');
            this.toolbarComponent.hideButton('exec-search');
            this.toolbarComponent.hideButton('cancel-search');
            this.toolbarComponent.showButton('avalar');
            this.toolbarComponent.render();
            this.componentController.avalarRecurso();
        },
        avalar: function()
        {
            this.toolbarComponent.hideButton('create');
            this.toolbarComponent.hideButton('save');
            this.toolbarComponent.hideButton('cancel');
            this.toolbarComponent.hideButton('refresh');
            this.toolbarComponent.hideButton('print');
            this.toolbarComponent.hideButton('search');
            this.toolbarComponent.showButton('exec-avalar');
            this.toolbarComponent.render();
            this.componentController.create();
        },
        mostrarRecurso: function()
        {
            
        }
    });
    return App.Component.RecursoComponent;

});