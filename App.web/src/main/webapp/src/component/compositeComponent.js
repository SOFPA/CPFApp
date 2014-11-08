/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

define(['component/_CRUDComponent', 'model/comentarioModel', 'model/recursoModel', 'controller/comentarioController'], function() {
    App.Component._ComentarioComponent = App.Component._CRUDComponent.extend({
        initialize: function() {
            this.componentId = App.Utils.randomInteger();
            this.name = "Visualizar Recurso";
            this.setupRecursoComponent();
            this.setupComentarioComponent();
            },
        setupRecursoComponent: function() {
            this.recursoComponent = new recursoCp();
            this.recursoComponent.initialize();
            this.recursoComponent.setReadOnly(true);
            this.recursoComponent.mostrarRecurso();
        },
        setupComentarioComponent: function() {
           this.cartMasterComponent = new comentarioCp();
                    this.comentarioComponent.initialize();
                    this.comentarioComponent.comentar();
                }
    });
    return App.Component._CompositeComponent;
});

