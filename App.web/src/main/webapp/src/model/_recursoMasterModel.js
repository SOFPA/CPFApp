define([], function() {
    App.Model._RecursoMasterModel = Backbone.Model.extend({
     	initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('recurso-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.RecursoModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.recursoEntity,options);
            }
        }
    });

    App.Model._RecursoMasterList = Backbone.Collection.extend({
        model: App.Model._RecursoMasterModel,
        initialize: function() {
        }

    });
    return App.Model._RecursoMasterModel;
    
});