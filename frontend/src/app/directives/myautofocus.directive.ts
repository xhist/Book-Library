import {Directive, ElementRef} from '@angular/core';

@Directive({
  selector: '[Myautofocus]'
})
export class MyautofocusDirective {

  constructor(private elementRef: ElementRef) { }

  ngOnInit(): void {
    this.elementRef.nativeElement.focus();
  }

}
