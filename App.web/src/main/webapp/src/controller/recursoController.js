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
define(['controller/_recursoController', 'delegate/recursoDelegate'], function() {
    App.Controller.RecursoController = App.Controller._RecursoController.extend({
        postInit: function(options) {
            var self = this;
            this.mostrarRecursoTemplate=_.template($('#mostrarRecurso').html());
            this.listRecursosTemplate = _.template($('#recursosPorAvalarList').html());
            this.listRecursosModelClass = options.listModelClass;
            
        },
        guardarRecurso: function(params){
            var self = this;
            var delegate = new App.Delegate.RecursoDelegate();
            delegate.create(params);
        },
        recursoSearch: function(callback, context) {
            var self = this;
            var model = $('#' + this.componentId + '-recursoForm').serializeObject();
            this.currentModel.set(model);
            var delegate = new App.Delegate.RecursoDelegate();
            delegate.search(self.currentModel, function(data) {
                self.currentList.reset(data.records);
                callback.call(context, {data: self.currentList, page: 1, pages: 1, totalRecords: self.currentList.lenght});
            }, function(data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'recurso-search', view: self, id: '', data: data, error: 'Error in recurso search'});
            });
        },
        avalarRecurso: function(callback, context) {
            var self = this;
            var model = $('#' + this.componentId + '-recursoForm').serializeObject();
            this.currentModel.set(model);
            var delegate = new App.Delegate.RecursoDelegate();
            delegate.search(self.currentModel, function(data) {
                self.currentList.reset(data.records);
                callback.call(context, {data: self.currentList, page: 1, pages: 1, totalRecords: self.currentList.lenght});
            }, function(data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'recurso-avalar', view: self, id: '', data: data, error: 'Error in recurso avalar'});
            });
        },
        _renderLista: function() {
            var self = this;
            /*Aqu� se utiliza el efecto gr�fico backbone deslizar. �$el� hace referencia al <div id=�main�> ubicado en el index.html. Dentro de este div se despliegue la tabla.*/
            this.$el.slideUp("fast", function() {
                /*Establece que en el <div> se despliegue el template de la variable ��. Como par�metros entran las variables establecidas dentro de los tags <%%> con sus valores como un objeto JSON. En este caso, la propiedad sports tendr� la lista que instanci� �sportSearch� en la variable del bucle <% _.each(sports, function(sport) { %>*/
                self.$el.html(self.listRecursosTemplate({recursos: self.recursosPorAvalarModelList.models}));
                self.$el.slideDown("fast");
            });
        },
        hacerLista: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' + 'instead-recurso-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-recurso-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-recurso-list', {view: this, data: data});
                var self = this;
                if (!this.recursoModelList) {
                    this.recursoModelList = new this.listModelClass();
                }
                this.recursoModelList.fetch({
                    data: data,
                    success: function() {
                        var elementos = self.recursoModelList.models;
                        self.recursosPorAvalartModelList = new App.Model.RecursosPorAvalartList;
                        //Se itera sobre la variable elementos, que corresponden a la lista de modelos obtenida del servico REST getSports
                        _.each(elementos, function(d) {
                            var model = new App.Model.RecursosPorAvalartModel({name: d.attributes.name, link: 'a'});
                            //y se agrega finalmente a los modelos de la lista.
                            self.recursosPorAvalartModelList.models.push(model);
                        });
                        //Se invoca la funci�n de renderizado para que muestre los resultados en la nueva lista.
                        self._renderLista(params);
                        Backbone.trigger(self.componentId + '-' + 'post-recurso-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'recurso-list', view: self, error: error});
                    }
                });
            }
        },
        _renderMostrar: function() {
             var self = this;
            /*Aqu� se utiliza el efecto gr�fico backbone deslizar. �$el� hace referencia al <div id=�main�> ubicado en el index.html. Dentro de este div se despliegue la tabla.*/
            this.$el.slideUp("fast", function() {
                /*Establece que en el <div> se despliegue el template de la variable ��. Como par�metros entran las variables establecidas dentro de los tags <%%> con sus valores como un objeto JSON. En este caso, la propiedad sports tendr� la lista que instanci� �sportSearch� en la variable del bucle <% _.each(sports, function(sport) { %>*/
                self.$el.html(self.mostrarRecursoTemplate({recursos: self.mostrarRecurso}));
                self.$el.slideDown("fast");
            });
        },
        mostrarRecurso: function(params){
            var url=params.url;
            var data=params.data;
            
        }
    });
    return App.Controller.RecursoController;
}); 