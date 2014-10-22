define(['controller/selectionController', 'model/cacheModel', 'model/recursoMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/recursoComponent',
 'component/comentarioComponent', 'component/usuarioComponent'],
 function(SelectionController, CacheModel, RecursoMasterModel, CRUDComponent, TabController, RecursoComponent,
 publicacionComponent, estudiantes_recientesComponent) {
    App.Component._RecursoMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('recursoMaster');
            App.Model.RecursoMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new RecursoComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-recurso-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-recurso-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-recurso-list', function() {
                self.hideChilds();
            });
            Backbone.on('recurso-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'recurso-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-recurso-save', function(params) {
                self.model.set('recursoEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'publicacion',
					self.model,
					self.publicacionComponent.getDeletedRecords(),
					self.publicacionComponent.getUpdatedRecords(),
					self.publicacionComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'estudiantes_recientes',
					self.model,
					self.estudiantes_recientesComponent.getDeletedRecords(),
					self.estudiantes_recientesComponent.getUpdatedRecords(),
					self.estudiantes_recientesComponent.getCreatedRecords()
				);

                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-recurso-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'recurso-master-save', view: self, error: error});
                    }
                });
			    if (this.postInit) {
					this.postInit();
				}
            });
        },
        render: function(domElementId){
			if (domElementId) {
				var rootElementId = $("#"+domElementId);
				this.masterElement = this.componentId + "-master";
				this.tabsElement = this.componentId + "-tabs";

				rootElementId.append("<div id='" + this.masterElement + "'></div>");
				rootElementId.append("<div id='" + this.tabsElement + "'></div>");
			}
			this.masterComponent.render(this.masterElement);
		},
		initializeChildComponents: function () {
			this.tabModel = new App.Model.TabModel({tabs: [
                {label: "Publicacion", name: "publicacion", enable: true},
                {label: "Estudiantes_recientes", name: "estudiantes_recientes", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.publicacionComponent = new publicacionComponent();
            this.publicacionComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.publicacionComponent);

			this.estudiantes_recientesComponent = new estudiantes_recientesComponent();
            this.estudiantes_recientesComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.estudiantes_recientesComponent);

            var self = this;
            
            this.configToolbar(this.publicacionComponent,true);
            Backbone.on(self.publicacionComponent.componentId + '-post-comentario-create', function(params) {
                params.view.currentModel.setCacheList(params.view.currentList);
            });
            
            this.configToolbar(this.estudiantes_recientesComponent,false);
            this.estudiantes_recientesComponent.disableEdit();

            Backbone.on(this.estudiantes_recientesComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"estudiantes_recientesComponent"});
                App.Utils.getComponentList('usuarioComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Estudiantes_recientess to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Estudiantes_recientes List'});
                    }
                    ;
                });
            });

            Backbone.on('estudiantes_recientesComponent-post-selection', function(models) {
            	self.estudiantes_recientesComponent.addRecords(models);
            	self.estudiantes_recientesComponent.render();
            });

		},
        renderChilds: function(params) {
            var self = this;
            
            var options = {
                success: function() {
                	self.tabs.render(self.tabsElement);

					self.publicacionComponent.clearCache();
					self.publicacionComponent.setRecords(self.model.get('listpublicacion'));
					self.publicacionComponent.render(self.tabs.getTabHtmlId('publicacion'));

					self.estudiantes_recientesComponent.clearCache();
					self.estudiantes_recientesComponent.setRecords(self.model.get('listestudiantes_recientes'));
					self.estudiantes_recientesComponent.render(self.tabs.getTabHtmlId('estudiantes_recientes'));

                    $('#'+self.tabsElement).show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'recurso-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.RecursoMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.RecursoMasterModel();
                options.success();
            }


        },
        showMaster: function (flag) {
			if (typeof (flag) === "boolean") {
				if (flag) {
					$("#"+this.masterElement).show();
				} else {
					$("#"+this.masterElement).hide();
				}
			}
		},
        hideChilds: function() {
            $("#"+this.tabsElement).hide();
        },
		configToolbar: function(component, composite) {
		    component.removeGlobalAction('refresh');
			component.removeGlobalAction('print');
			component.removeGlobalAction('search');
			if (!composite) {
				component.removeGlobalAction('create');
				component.removeGlobalAction('save');
				component.removeGlobalAction('cancel');
				component.addGlobalAction({
					name: 'add',
					icon: 'glyphicon-send',
					displayName: 'Add',
					show: true
				}, function () {
					Backbone.trigger(component.componentId + '-toolbar-add');
				});
			}
        },
        getChilds: function(name){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === name) {
					return this.childComponents[idx].getRecords();
				}
			}
		},
		setChilds: function(childName,childData){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].setRecords(childData);
				}
			}
		},
		renderMaster: function(domElementId){
			this.masterComponent.render(domElementId);
		},
		renderChild: function(childName, domElementId){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].render(domElementId);
				}
			}
		}
    });

    return App.Component._RecursoMasterComponent;
});