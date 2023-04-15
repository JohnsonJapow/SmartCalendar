// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EventManger.proto

package EventManager_GRPC;

/**
 * Protobuf type {@code extra_smart.EventModificationRequest}
 */
public  final class EventModificationRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:extra_smart.EventModificationRequest)
    EventModificationRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use EventModificationRequest.newBuilder() to construct.
  private EventModificationRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EventModificationRequest() {
    id_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private EventModificationRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            id_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();
            actionCase_ = 2;
            action_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();
            actionCase_ = 3;
            action_ = s;
            break;
          }
          case 40: {
            actionCase_ = 5;
            action_ = input.readInt32();
            break;
          }
          case 48: {
            actionCase_ = 6;
            action_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return EventManager_GRPC.SmartServiceImpl.internal_static_extra_smart_EventModificationRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return EventManager_GRPC.SmartServiceImpl.internal_static_extra_smart_EventModificationRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            EventManager_GRPC.EventModificationRequest.class, EventManager_GRPC.EventModificationRequest.Builder.class);
  }

  private int actionCase_ = 0;
  private java.lang.Object action_;
  public enum ActionCase
      implements com.google.protobuf.Internal.EnumLite {
    NAME(2),
    DESCRIPTION(3),
    REMINDER(5),
    DATE(6),
    ACTION_NOT_SET(0);
    private final int value;
    private ActionCase(int value) {
      this.value = value;
    }
    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static ActionCase valueOf(int value) {
      return forNumber(value);
    }

    public static ActionCase forNumber(int value) {
      switch (value) {
        case 2: return NAME;
        case 3: return DESCRIPTION;
        case 5: return REMINDER;
        case 6: return DATE;
        case 0: return ACTION_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public ActionCase
  getActionCase() {
    return ActionCase.forNumber(
        actionCase_);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object id_;
  /**
   * <code>string id = 1;</code>
   */
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 1;</code>
   */
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NAME_FIELD_NUMBER = 2;
  /**
   * <code>string name = 2;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = "";
    if (actionCase_ == 2) {
      ref = action_;
    }
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (actionCase_ == 2) {
        action_ = s;
      }
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = "";
    if (actionCase_ == 2) {
      ref = action_;
    }
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      if (actionCase_ == 2) {
        action_ = b;
      }
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DESCRIPTION_FIELD_NUMBER = 3;
  /**
   * <code>string description = 3;</code>
   */
  public java.lang.String getDescription() {
    java.lang.Object ref = "";
    if (actionCase_ == 3) {
      ref = action_;
    }
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (actionCase_ == 3) {
        action_ = s;
      }
      return s;
    }
  }
  /**
   * <code>string description = 3;</code>
   */
  public com.google.protobuf.ByteString
      getDescriptionBytes() {
    java.lang.Object ref = "";
    if (actionCase_ == 3) {
      ref = action_;
    }
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      if (actionCase_ == 3) {
        action_ = b;
      }
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REMINDER_FIELD_NUMBER = 5;
  /**
   * <code>int32 reminder = 5;</code>
   */
  public int getReminder() {
    if (actionCase_ == 5) {
      return (java.lang.Integer) action_;
    }
    return 0;
  }

  public static final int DATE_FIELD_NUMBER = 6;
  /**
   * <code>int64 date = 6;</code>
   */
  public long getDate() {
    if (actionCase_ == 6) {
      return (java.lang.Long) action_;
    }
    return 0L;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
    }
    if (actionCase_ == 2) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, action_);
    }
    if (actionCase_ == 3) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, action_);
    }
    if (actionCase_ == 5) {
      output.writeInt32(
          5, (int)((java.lang.Integer) action_));
    }
    if (actionCase_ == 6) {
      output.writeInt64(
          6, (long)((java.lang.Long) action_));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
    }
    if (actionCase_ == 2) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, action_);
    }
    if (actionCase_ == 3) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, action_);
    }
    if (actionCase_ == 5) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(
            5, (int)((java.lang.Integer) action_));
    }
    if (actionCase_ == 6) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(
            6, (long)((java.lang.Long) action_));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof EventManager_GRPC.EventModificationRequest)) {
      return super.equals(obj);
    }
    EventManager_GRPC.EventModificationRequest other = (EventManager_GRPC.EventModificationRequest) obj;

    boolean result = true;
    result = result && getId()
        .equals(other.getId());
    result = result && getActionCase().equals(
        other.getActionCase());
    if (!result) return false;
    switch (actionCase_) {
      case 2:
        result = result && getName()
            .equals(other.getName());
        break;
      case 3:
        result = result && getDescription()
            .equals(other.getDescription());
        break;
      case 5:
        result = result && (getReminder()
            == other.getReminder());
        break;
      case 6:
        result = result && (getDate()
            == other.getDate());
        break;
      case 0:
      default:
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId().hashCode();
    switch (actionCase_) {
      case 2:
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        break;
      case 3:
        hash = (37 * hash) + DESCRIPTION_FIELD_NUMBER;
        hash = (53 * hash) + getDescription().hashCode();
        break;
      case 5:
        hash = (37 * hash) + REMINDER_FIELD_NUMBER;
        hash = (53 * hash) + getReminder();
        break;
      case 6:
        hash = (37 * hash) + DATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            getDate());
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static EventManager_GRPC.EventModificationRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static EventManager_GRPC.EventModificationRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static EventManager_GRPC.EventModificationRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static EventManager_GRPC.EventModificationRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static EventManager_GRPC.EventModificationRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static EventManager_GRPC.EventModificationRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static EventManager_GRPC.EventModificationRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static EventManager_GRPC.EventModificationRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static EventManager_GRPC.EventModificationRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static EventManager_GRPC.EventModificationRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static EventManager_GRPC.EventModificationRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static EventManager_GRPC.EventModificationRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(EventManager_GRPC.EventModificationRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code extra_smart.EventModificationRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:extra_smart.EventModificationRequest)
      EventManager_GRPC.EventModificationRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return EventManager_GRPC.SmartServiceImpl.internal_static_extra_smart_EventModificationRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return EventManager_GRPC.SmartServiceImpl.internal_static_extra_smart_EventModificationRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              EventManager_GRPC.EventModificationRequest.class, EventManager_GRPC.EventModificationRequest.Builder.class);
    }

    // Construct using EventManager_GRPC.EventModificationRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = "";

      actionCase_ = 0;
      action_ = null;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return EventManager_GRPC.SmartServiceImpl.internal_static_extra_smart_EventModificationRequest_descriptor;
    }

    @java.lang.Override
    public EventManager_GRPC.EventModificationRequest getDefaultInstanceForType() {
      return EventManager_GRPC.EventModificationRequest.getDefaultInstance();
    }

    @java.lang.Override
    public EventManager_GRPC.EventModificationRequest build() {
      EventManager_GRPC.EventModificationRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public EventManager_GRPC.EventModificationRequest buildPartial() {
      EventManager_GRPC.EventModificationRequest result = new EventManager_GRPC.EventModificationRequest(this);
      result.id_ = id_;
      if (actionCase_ == 2) {
        result.action_ = action_;
      }
      if (actionCase_ == 3) {
        result.action_ = action_;
      }
      if (actionCase_ == 5) {
        result.action_ = action_;
      }
      if (actionCase_ == 6) {
        result.action_ = action_;
      }
      result.actionCase_ = actionCase_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof EventManager_GRPC.EventModificationRequest) {
        return mergeFrom((EventManager_GRPC.EventModificationRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(EventManager_GRPC.EventModificationRequest other) {
      if (other == EventManager_GRPC.EventModificationRequest.getDefaultInstance()) return this;
      if (!other.getId().isEmpty()) {
        id_ = other.id_;
        onChanged();
      }
      switch (other.getActionCase()) {
        case NAME: {
          actionCase_ = 2;
          action_ = other.action_;
          onChanged();
          break;
        }
        case DESCRIPTION: {
          actionCase_ = 3;
          action_ = other.action_;
          onChanged();
          break;
        }
        case REMINDER: {
          setReminder(other.getReminder());
          break;
        }
        case DATE: {
          setDate(other.getDate());
          break;
        }
        case ACTION_NOT_SET: {
          break;
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      EventManager_GRPC.EventModificationRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (EventManager_GRPC.EventModificationRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int actionCase_ = 0;
    private java.lang.Object action_;
    public ActionCase
        getActionCase() {
      return ActionCase.forNumber(
          actionCase_);
    }

    public Builder clearAction() {
      actionCase_ = 0;
      action_ = null;
      onChanged();
      return this;
    }


    private java.lang.Object id_ = "";
    /**
     * <code>string id = 1;</code>
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = getDefaultInstance().getId();
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      id_ = value;
      onChanged();
      return this;
    }

    /**
     * <code>string name = 2;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = "";
      if (actionCase_ == 2) {
        ref = action_;
      }
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (actionCase_ == 2) {
          action_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = "";
      if (actionCase_ == 2) {
        ref = action_;
      }
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        if (actionCase_ == 2) {
          action_ = b;
        }
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  actionCase_ = 2;
      action_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder clearName() {
      if (actionCase_ == 2) {
        actionCase_ = 0;
        action_ = null;
        onChanged();
      }
      return this;
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      actionCase_ = 2;
      action_ = value;
      onChanged();
      return this;
    }

    /**
     * <code>string description = 3;</code>
     */
    public java.lang.String getDescription() {
      java.lang.Object ref = "";
      if (actionCase_ == 3) {
        ref = action_;
      }
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (actionCase_ == 3) {
          action_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string description = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDescriptionBytes() {
      java.lang.Object ref = "";
      if (actionCase_ == 3) {
        ref = action_;
      }
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        if (actionCase_ == 3) {
          action_ = b;
        }
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string description = 3;</code>
     */
    public Builder setDescription(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  actionCase_ = 3;
      action_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string description = 3;</code>
     */
    public Builder clearDescription() {
      if (actionCase_ == 3) {
        actionCase_ = 0;
        action_ = null;
        onChanged();
      }
      return this;
    }
    /**
     * <code>string description = 3;</code>
     */
    public Builder setDescriptionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      actionCase_ = 3;
      action_ = value;
      onChanged();
      return this;
    }

    /**
     * <code>int32 reminder = 5;</code>
     */
    public int getReminder() {
      if (actionCase_ == 5) {
        return (java.lang.Integer) action_;
      }
      return 0;
    }
    /**
     * <code>int32 reminder = 5;</code>
     */
    public Builder setReminder(int value) {
      actionCase_ = 5;
      action_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 reminder = 5;</code>
     */
    public Builder clearReminder() {
      if (actionCase_ == 5) {
        actionCase_ = 0;
        action_ = null;
        onChanged();
      }
      return this;
    }

    /**
     * <code>int64 date = 6;</code>
     */
    public long getDate() {
      if (actionCase_ == 6) {
        return (java.lang.Long) action_;
      }
      return 0L;
    }
    /**
     * <code>int64 date = 6;</code>
     */
    public Builder setDate(long value) {
      actionCase_ = 6;
      action_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 date = 6;</code>
     */
    public Builder clearDate() {
      if (actionCase_ == 6) {
        actionCase_ = 0;
        action_ = null;
        onChanged();
      }
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:extra_smart.EventModificationRequest)
  }

  // @@protoc_insertion_point(class_scope:extra_smart.EventModificationRequest)
  private static final EventManager_GRPC.EventModificationRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new EventManager_GRPC.EventModificationRequest();
  }

  public static EventManager_GRPC.EventModificationRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EventModificationRequest>
      PARSER = new com.google.protobuf.AbstractParser<EventModificationRequest>() {
    @java.lang.Override
    public EventModificationRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new EventModificationRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EventModificationRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EventModificationRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public EventManager_GRPC.EventModificationRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

