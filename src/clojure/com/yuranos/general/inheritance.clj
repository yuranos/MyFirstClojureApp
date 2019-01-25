(ns com.yuranos.general.inheritance)

;Need double colon (::)
(derive ::Cat ::Feline)
;nil
(derive ::Lion ::Feline)
;nil
(isa? ::Lion ::Feline)
;true
(isa? ::Tuna ::Feline)
;false                                                       ;

(derive java.util.Map ::collection)
(derive java.util.Collection ::collection)

(isa? java.util.HashMap ::collection)
;true
(isa? String ::collection)
;false


(derive ::rect ::shape)
(derive ::square ::rect)
(parents ::rect)
;-> #{:user/shape}
(ancestors ::square)
;-> #{:user/rect :user/shape}
(descendants ::shape)
;-> #{:user/rect :user/square}

;You can also use a class as the child (but not the parent,
; the only way to make something the child of a class is via Java inheritance).
