// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EventManger.proto

package EventManager_GRPC;

public final class SmartServiceImpl {
  private SmartServiceImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_extra_smart_Event_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_extra_smart_Event_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_extra_smart_EventResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_extra_smart_EventResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_extra_smart_EventModificationRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_extra_smart_EventModificationRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_extra_smart_DateRange_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_extra_smart_DateRange_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021EventManger.proto\022\013extra_smart\"V\n\005Even" +
      "t\022\n\n\002id\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022\023\n\013descripti" +
      "on\030\003 \001(\t\022\020\n\010reminder\030\004 \001(\005\022\014\n\004date\030\005 \001(\003" +
      "\"1\n\rEventResponse\022\017\n\007success\030\001 \001(\010\022\017\n\007me" +
      "ssage\030\002 \001(\t\"{\n\030EventModificationRequest\022" +
      "\n\n\002id\030\001 \001(\t\022\016\n\004name\030\002 \001(\tH\000\022\025\n\013descripti" +
      "on\030\003 \001(\tH\000\022\022\n\010reminder\030\005 \001(\005H\000\022\016\n\004date\030\006" +
      " \001(\003H\000B\010\n\006action\"1\n\tDateRange\022\022\n\nstart_d" +
      "ate\030\001 \001(\003\022\020\n\010end_date\030\002 \001(\0032\340\001\n\014EventMan" +
      "ager\022<\n\010AddEvent\022\022.extra_smart.Event\032\032.e" +
      "xtra_smart.EventResponse\"\000\022T\n\013ModifyEven" +
      "t\022%.extra_smart.EventModificationRequest" +
      "\032\032.extra_smart.EventResponse\"\000(\001\022<\n\nList" +
      "Events\022\026.extra_smart.DateRange\032\022.extra_s" +
      "mart.Event\"\0000\001B\'\n\021EventManager_GRPCB\020Sma" +
      "rtServiceImplP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_extra_smart_Event_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_extra_smart_Event_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_extra_smart_Event_descriptor,
        new java.lang.String[] { "Id", "Name", "Description", "Reminder", "Date", });
    internal_static_extra_smart_EventResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_extra_smart_EventResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_extra_smart_EventResponse_descriptor,
        new java.lang.String[] { "Success", "Message", });
    internal_static_extra_smart_EventModificationRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_extra_smart_EventModificationRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_extra_smart_EventModificationRequest_descriptor,
        new java.lang.String[] { "Id", "Name", "Description", "Reminder", "Date", "Action", });
    internal_static_extra_smart_DateRange_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_extra_smart_DateRange_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_extra_smart_DateRange_descriptor,
        new java.lang.String[] { "StartDate", "EndDate", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
