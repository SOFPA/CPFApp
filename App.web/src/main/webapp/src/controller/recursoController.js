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
define(['controller/_recursoController','delegate/recursoDelegate'], function() {
    App.Controller.RecursoController = App.Controller._RecursoController.extend({
        recursoSearch: function(callback,context){
            var self = this;
            var model = $('#' + this.componentId + '-recursoForm').serializeObject();
            this.currentModel.set(model);
            var delegate = new App.Delegate.RecursoDelegate();
            delegate.search(self.currentModel, function(data){
                self.currentList.reset(data.records);
                callback.call(context,{data: self.currentList, page: 1, pages: 1, totalRecords: self.currentList.lenght});  
            }, function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'recurso-search', view: self, id: '', data: data, error: 'Error in recurso search'});
            });
        }

    });
    return App.Controller.RecursoController;
}); 