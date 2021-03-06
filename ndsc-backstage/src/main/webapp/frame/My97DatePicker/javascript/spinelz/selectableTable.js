// Copyright (c) 2005 spinelz.org (http://script.spinelz.org/)
// 
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
// 
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

var SelectableTable = Class.create();

SelectableTable.classNames = {
  table:        'selectableTable_table',
  tr:           'selectableTable_tr',
  trHover:      'selectableTable_trHover',
  trSelected:   'selectableTable_trSelected'
}

SelectableTable.prototype = {
  initialize: function(element) {
    this.element = $(element);
    Element.setStyle(this.element, {visibility: 'hidden'});
    var defaultOptions  = {
      arrayDefaultData:                 [],
      flagAllowUnselect:                true,
      flagInitialAllowMultiple:         false,
      flagKeypressAvailable:            false,
      flagKeypressDeleteAvailable:      false,
      flagKeypressInsertAvailable:      false,
      functionPostAdd:                  Prototype.emptyFunction,
      functionPostDelete:               Prototype.emptyFunction,
      functionPostPressLeft:            Prototype.emptyFunction,
      functionPostPressRight:           Prototype.emptyFunction,
      functionPostSelect:               Prototype.emptyFunction,
      functionPostUnselect:             Prototype.emptyFunction,
      functionPreAdd:                   function() {return true;},
      functionPreDelete:                function() {return true;},
      functionSubmit:                   Prototype.emptyFunction,
      initialSelected:                  null,
      prefixTrId:                       'selectable_table_',
      prefixCSS:                        'custom_'
    }
    this.options                = Object.extend(defaultOptions, arguments[1] || {});
    this.classNames             = new CssUtil([SelectableTable.classNames, CssUtil.appendPrefix(this.options.prefixCSS, SelectableTable.classNames)]);
    this.documentListener       = this.eventKeypress.bindAsEventListener(this);
    this.flagAllowMultiple      = this.options.flagInitialAllowMultiple;
    this.flagAvailable          = true;
    this.focused                = null;
    this.lastSelected           = null;
    this.newNumber              = 1;
    this.selected               = new Object();
    this.build();
    if(arguments[2]) {
      this.selectEffect(this.buildTrId(arguments[2]));
    }
    Element.setStyle(this.element, {visibility: 'visible'});
  },

  add: function() {
    if(!this.flagAvailable) {return;}
    if(!this.options.functionPreAdd(this)) {return;}
    if(arguments[0] == null) {arguments[0] = this.options.arrayDefaultData;}
    if(typeof(arguments[0]) != 'string') {
      arguments = arguments[0];
    }
    if(arguments[0] == null) {return;}
    var objTr, objTd;
    objTr = document.createElement('tr');
    objTr.id = 'new_' + this.newNumber;
    this.buildTr(objTr);
    for(var i = 0; i < arguments.length; i++) {
      objTd = document.createElement('td');
      objTd.innerHTML = arguments[i];
      objTr.appendChild(objTd);
    }
    this.element.tBodies[0].appendChild(objTr);
    this.newNumber++;
    this.options.functionPostAdd(this);
  },

  build: function() {
    var lines = this.element.tBodies[0].rows;
    this.classNames.addClassNames(this.element, 'table');
    Event.observe(document, 'keypress', this.documentListener);
    for(var i = 0; i < lines.length; i++) {
      this.buildTr(lines[i]);
    }
    var selected = this.options.initialSelected
    if(selected) {
      this.selectEffect(this.buildTrId(selected));
    }
  },

  buildTr: function(objTr) {
    objTr.id = this.buildTrId(objTr.id);
    this.classNames.addClassNames(objTr, 'tr');
    Event.observe(objTr, 'click',     this.eventClick.bindAsEventListener(this));
    Event.observe(objTr, 'dblclick',  this.eventDoubleClick.bindAsEventListener(this));
    Event.observe(objTr, 'mouseout',  this.eventFocusOut.bindAsEventListener(this));
    Event.observe(objTr, 'mouseover', this.eventFocusOver.bindAsEventListener(this));
  },

  buildTrId: function(strId) {
    return this.options.prefixTrId + strId
  },

  deleteAll: function() {
    if(!this.flagAvailable) {return;}
    if(!this.options.functionPreDelete(this)) {return;}
    for(var trId in this.selected) {
      this.element.tBodies[0].removeChild($(trId));
      delete this.selected[trId];
    }
    this.focused = null;
    this.options.functionPostDelete(this);
  },

  eventClick: function(event) {
    if(!this.flagAvailable) {return;}
    if(event.shiftKey) {
      this.selectOrUnselectRange(Event.findElement(event, 'tr').id);
    } else {
      this.selectOrUnselect(Event.findElement(event, 'tr').id, event.ctrlKey);
    }
  },

  eventDoubleClick: function(event) {
    if(!this.flagAvailable) {return;}
    if(this.flagAllowMultiple) {
      this.select(Event.findElement(event, 'tr').id, false);
      this.submit();
    }
  },

  eventFocusOut: function(event) {
    if(!this.flagAvailable) {return;}
    this.focusOff();
  },

  eventFocusOver: function(event) {
    if(!this.flagAvailable) {return;}
    this.focusOn(Event.findElement(event, 'tr').id);
    Event.findElement(event, 'tr').focus();
  },

  eventKeypress: function(event) {
    if(!this.flagAvailable) {return;}
    if(!this.options.flagKeypressAvailable) {return;}
    switch(event.keyCode) {
      case 13: //Enter
        if(event.shiftKey) {
          this.selectOrUnselectRange(this.focused);
        } else {
          this.selectOrUnselect(this.focused, event.ctrlKey);
        }
        break;
      case 37: //Left
        this.options.functionPostPressLeft(this);
        break;
      case 38: //Up
        this.focusMove('up');
        break;
      case 39: //Right
        this.options.functionPostPressRight(this);
        break;
      case 40: //Down
        this.focusMove('down');
        break;
      case 45: //Insert
        if(this.options.flagKeypressInsertAvailable) {this.add();}
        break;
      case 46: //Delete
        if(this.options.flagKeypressDeleteAvailable) {this.deleteAll();}
        break;
    }
  },

  focusMove: function(direction) {
    if(!this.flagAvailable) {return;}
    if(this.focused == null) {
      this.focusOn(this.element.tBodies[0].rows[0].id);
    } else {
      var rowIndex = $(this.focused).rowIndex;
      var correctionValue, flagEdge;
      switch(direction) {
        case 'down':
          correctionValue = 1;
          flagEdge = this.isBottom(rowIndex);
          break;
        case 'up':
          correctionValue = -1;
          flagEdge = this.isTop(rowIndex);
          break;
      }
      if(!flagEdge) {
        this.focusOn(this.element.rows[rowIndex + correctionValue].id);
      }
    }
  },

  focusOff: function() {
    if(!this.flagAvailable) {return;}
    if(this.focused != null) {
      var objTr = $(this.focused);
      this.classNames.removeClassNames(objTr, 'trHover');
      this.focused = null;
    }
  },

  focusOn: function(trId) {
    if(!this.flagAvailable) {return;}
    if($(trId) != null) {
      this.focusOff();
      this.classNames.addClassNames($(trId), 'trHover');
      this.focused = trId;
    }
  },

  getSelected: function() {
    var selectedIdList  = new Array();
    for(var trId in this.selected) {
      selectedIdList.push(trId.replace(this.options.prefixTrId, ''));
    }
    return selectedIdList;
  },

  getSelectedElement: function(id) {
    var trId = this.options.prefixTrId + id;
    return $(trId)
  },

  isBottom: function(rowIndex) {
    return (rowIndex == this.element.rows.length - 1) ? true : false;
  },

  isTop: function(rowIndex) {
    return (rowIndex == this.element.tBodies[0].rows[0].rowIndex) ? true : false;
  },

  makeAvailable: function() {
    this.flagAvailable = true;
  },

  makeMultiple: function() {
    this.flagAllowMultiple = true;
  },

  makeSingular: function() {
    this.flagAllowMultiple = false;
    this.unselectAll();
  },

  makeUnavailable: function() {
    this.flagAvailable = false;
  },

  removeEventFromDocument: function() {
    Event.stopObserving(document, 'keypress', this.documentListener);
  },

  select: function(trId, ctrl) {
    if(!this.flagAvailable) {return;}
    this.selectEffect(trId, ctrl);
    this.lastSelected = trId;
    this.options.functionPostSelect(this);
    if(!this.flagAllowMultiple) {
      this.submit();
    }
  },

  selectAll: function() {
    if(!this.flagAvailable) {return;}
    if(!this.flagAllowMultiple) {return;}
    this.selected = new Object();
    var lines = this.element.tBodies[0].rows;
    for(var i = 0; i < lines.length; i++) {
      this.select(lines[i].id, true);
    }
  },

  selectEffect: function(trId, ctrl) {
    if($(trId)) {
      if(!this.flagAllowMultiple || !ctrl) {
        this.unselectAll();
      }
      this.classNames.addClassNames($(trId), 'trSelected');
      this.selected[trId] = true;
    }
  },

  selectOrUnselect: function(trId, ctrl) {
    if(!this.flagAvailable) {return;}
    if(trId == null) {return;}
    if(ctrl && this.selected[trId]) {
      if(!this.flagAllowMultiple && !this.options.flagAllowUnselect) {return;}
      this.unselect(trId);
    } else {
      this.select(trId, ctrl);
    }
  },

  selectOrUnselectRange: function(trId) {
    if(!this.flagAvailable) {return;}
    if(trId == null) {return;}
    if(this.lastSelected == null || this.lastSelected == trId) {
      this.selectOrUnselect(trId);
      return;
    }
    var flagSelect = false;
    var lines = this.element.tBodies[0].rows;
    var lastSelected = this.lastSelected
    for(var i = 0; i < lines.length; i++) {
      if(lines[i].id == trId || lines[i].id == lastSelected) {
        flagSelect = (flagSelect) ? false : true;
      } else if(!flagSelect) {
        continue;
      }
      if(this.selected[lastSelected]) {
        this.select(lines[i].id, true);
      } else {
        this.unselect(lines[i].id);
      }
    }
  },

  submit: function(trId) {
    if(!this.flagAvailable) {return;}
    var selected = this.getSelected();
    this.options.functionSubmit(selected[0]);
  },

  unselect: function(trId) {
    if(!this.flagAvailable) {return;}
    this.classNames.removeClassNames($(trId), 'trSelected');
    delete this.selected[trId];
    this.lastSelected = trId;
    this.options.functionPostUnselect(this);
  },

  unselectAll: function() {
    if(!this.flagAvailable) {return;}
    var lines = this.element.tBodies[0].rows;
    for(var i = 0; i < lines.length; i++) {
      this.unselect(lines[i].id);
    }
  }
}

var SelectableTableManager = Class.create();
SelectableTableManager.prototype = {
  initialize: function() {
    this.active = null,
    this.list   = {}
  },
  activate: function(key) {
    this.stop();
    if(this.list[key]) {
      this.list[key].makeAvailable();
      this.active = this.list[key];
    } else {
      this.active = null;
    }
  },
  push: function(key, element) {
    this.list[key] = element;
  },
  start: function() {
    if(this.active) {
      this.active.makeAvailable();
    }
  },
  stop: function() {
    $H(this.list).each(
      function(el) {
        if(el[1]) {
          el[1].makeUnavailable();
        }
      }
    );
  }
}
