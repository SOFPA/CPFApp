define(['model/_recursoMasterModel'], function() { 
    App.Model.RecursoMasterModel = App.Model._RecursoMasterModel.extend({

    });

    App.Model.RecursoMasterList = App.Model._RecursoMasterList.extend({
        model: App.Model.RecursoMasterModel
    });

    return  App.Model.RecursoMasterModel;

});