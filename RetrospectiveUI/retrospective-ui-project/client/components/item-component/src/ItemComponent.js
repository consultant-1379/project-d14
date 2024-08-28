/**
 * Component ItemComponent is defined as
 * `<e-item-component>`
 *
 * Imperatively create component
 * @example
 * let component = new ItemComponent();
 *
 * Declaratively create component
 * @example
 * <e-item-component></e-item-component>
 *
 * @extends {LitComponent}
 */
 import { definition } from '@eui/component';
 import { LitComponent, html } from '@eui/lit-component';
 import style from './itemComponent.css';
 
 /**
  * @property {Boolean} propOne - show active/inactive state.
  * @property {String} propTwo - shows the "Hello World" string.
  */
 @definition('e-item-component', {
   style,
   home: 'item-component',
   props: {
     itemId: { attribute: true, type: String , default: 'Default item ID'},
     itemDescription: { attribute: true, type: String, default: 'Default item description' },
     itemCategory: { attribute: true, type: String, default: 'Default category'},
     itemComments: { attribute: true, type: Array, default: ['This item was particularly challenging.', 'In the future we should spend more time on this.']},
     commentsShowing: { attribute: true, type: Boolean, default: false},
     newCommentContent: { attribute: true, type: String}
   },
 })

 export default class ItemComponent extends LitComponent {
   /**
    * Render the <e-item-component> component. This function is called each time a
    * prop changes.
    */

    constructor(){
      super();
      this.fetchComments();
     }
  
     addCommentToItem(){
      //post request for item
      if(this.newCommentContent != null){
        const body = {
          itemId: this.itemId,
          email: "user@ericsson.com",
          comment: this.newCommentContent
        };
          const response = fetch('http://localhost:9090/comment', {
            method: 'POST', 
            body: JSON.stringify(body),
            headers: {'Content-Type': 'application/json'}
          });
          //location.reload();
          console.log("Comment POST request initiated");
          location.reload();
      }
      else{
        console.log("Empty comment content");
      }
    }

   checkCategory(){
    let _category_placeholder = "";
    switch(this.itemCategory){
      case "MAD":
        _category_placeholder = "itemMad";
        break;
      case "SAD":
        _category_placeholder = "itemSad";
        break;
      case "GLAD":
        _category_placeholder = "itemGlad";
        break;
    }
    return _category_placeholder;
   };

   fetchComments(){
    fetch("http://localhost:9090/comment")
     .then((Response => Response.json()))
       .then((jsonResponse) => {
         let temp = [];
         //var present = new Date();
         jsonResponse.forEach((retro) => {
           temp =[...temp, retro];
         });
         this.itemComments = [...temp];
           console.log("All comments: ", this.itemComments); // print out all the teams to the console
           console.log("URL: ", urlParam);
         })
         .catch((error) => {
           console.log('Error fetching retrospectives: check if URL is valid');
         });
   }

   checkForComments(){
    let x = this.itemComments;
    //return x.length === 0 ? html `No comments yet` : this.itemComments;
    if(this.itemComments.length === 0){
      return html `<p>No comments to show</p>`;
    }
    else{
      return this.renderComments(this.itemComments);
    }
   }

   countComments(){
    let _filtered_result = this.itemComments.filter(comment => comment.itemId === this.itemId);
    if(_filtered_result.length === 0){
      return html `No comments to display`;
    }
    else{
      return this.renderComments(this.itemComments);
    }
   }

   // will take this.itemComments
   renderComments = (comments) => comments
   .filter(comment => comment.itemId === this.itemId)
   .map(
    comment => 
      html `<li>${comment.comment}</li>`
   );

   displayComments(){
    while(this.commentsShowing){
      return html `
      <eui-base-v0-dialog label="Comments" show=${this.commentsShowing}>
          <div slot="content" align="left">
          <p align="left">
            ${this.countComments()}
          </p>
          <eui-base-v0-text-field placeholder='Write a comment' @change=${(event) => this.newCommentContent = event.detail.value}></eui-base-v0-text-field>
          ${console.log("New comment:",this.newCommentContent)}
          </div>
        <eui-base-v0-button slot="bottom" onclick="${this.addCommentToItem()}" primary>Add comment</eui-base-v0-button>
      </eui-base-v0-dialog>`;
    }
   }

   render() {
     return html`
     <eui-layout-v0-card card-title="${this.itemDescription}" drag>
       <eui-base-v0-tooltip slot="action" message="Category" position="left">
         <eui-base-v0-pill class=${this.checkCategory()}>${this.itemCategory}</eui-base-v0-pill>
       </eui-base-v0-tooltip>
       <eui-base-v0-tooltip slot="action" message="Comments" position="right" @click=${(event) =>this.commentsShowing = true}>
        ${this.displayComments()}
        <eui-v0-icon name="message-smiley"></eui-v0-icon>
       </eui-base-v0-tooltip>
     </eui-layout-v0-card>`;
   }
 }
 
 /**
  * Register the component as e-item-component.
  * Registration can be done at a later time and with a different name
  */
 ItemComponent.register();
 